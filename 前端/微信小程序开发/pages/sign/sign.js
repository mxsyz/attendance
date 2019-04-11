//sign.js
//获取应用实例
const app = getApp()
Page({

  /**
0   * 页面的初始数据
   */
  data: {
      sit:'',
      sot:'',
      signInTime:'',
      signOutTime:'',
      i:'',
      o:'',
      latitude: '',
      longitude: '',
      primarySize: 'default',
      circles: [{
        latitude: '',
        longitude: '',
        color: '#FF0000DD',
        fillColor: '#7cb5ec88',
        radius:0,
        strokeWidth: 2,
      }],
      polyline: [{
        points: [{
          latitude: 0,
          longitude: 0
        }, {
          longitude: 0,
          latitude: 0
        }],
        color: "#FF0000",
        width: 3,
        dottedLine: true,
        arrowLine: true,
        borderColor: "#000",
        borderWidth: 20,
        
      }],
    },
    regionchange(e) {
      console.log(e.type)
    },
    markertap(e) {
      console.log(e.markerId)
    },
    controltap(e) {
      console.log(e.controlId)
  },
  signin:function(){
    var that=this;
    var timestamp = Date.parse(new Date());
    var now = new Date(timestamp);
    var nowhm = now.getHours() * 60 + now.getMinutes();
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        that.setData({
          'polyline[0].points[0].latitude': latitude,
          'polyline[0].points[0].longitude': longitude,
          latitude: latitude,
          longitude: longitude,
        })
        var distance = that.getDistance(that.data.latitude, longitude, app.globalData.latitude, app.globalData.longitude);
        if (distance <= that.data.circles[0].radius&&nowhm>=that.data.sit&&nowhm<=(that.data.sit+60)){
          wx.request({
            url: 'http://localhost:8080/signIn?jobNumber=' + app.globalData.jobNumber + '&date=' + now.getFullYear()+'-'+(now.getMonth()+1)+'-'+now.getDate(),
            method: 'GET',
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              that.setData({
                disabled1: true,
                message1: "已签到",
              })
            }
          })
        }
        else if (distance > that.data.circles[0].radius && nowhm >= that.data.sit && nowhm <= (that.data.sit + 60)) {
          that.setData({
            disabled1: true,
            message1: "签到",
          })
        }
        else{
          that.setData({
            disabled1: true,
            message1: "未签到",
          })
        }
      }
    })
  },
  signout: function () {
    var that=this;
    var timestamp = Date.parse(new Date());
    var now = new Date(timestamp);
    var nowhm = now.getHours() * 60 + now.getMinutes();
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        that.setData({
          'polyline[0].points[0].latitude': latitude,
          'polyline[0].points[0].longitude': longitude,
          latitude: latitude,
          longitude: longitude,
        })
        var distance = that.getDistance(that.data.latitude, longitude, app.globalData.latitude, app.globalData.longitude);
        if (distance <= that.data.circles[0].radius&&nowhm >= that.data.sot && nowhm <= (that.data.sot + 60)) {
          wx.request({
            url: 'http://localhost:8080/signOut?jobNumber=' + app.globalData.jobNumber + '&date=' + now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate(),
            method: 'GET',
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              that.setData({
                disabled2: true,
                message2: "已签退",
              })
            }
          })
        }
        else if (distance > that.data.circles[0].radius && nowhm >= that.data.sot && nowhm <= (that.data.sot + 60)){
          that.setData({
            disabled2: true,
            message2: "签退",
          })
        }
        else {
          that.setData({
            disabled2: true,
            message2: "未签退",
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var array1=new Array();
    array1 = app.globalData.signInTime.split(":");
    var array2 = new Array();
    array2 = app.globalData.signOutTime.split(":");
    that.setData({
      sit:array1[0]*60+array1[1]*1,
      sot:array2[0]*60+array2[1]*1,
      signInTime:app.globalData.signInTime,
      signOutTime:app.globalData.signOutTime,
      'circles[0].latitude': app.globalData.latitude,
      'circles[0].longitude': app.globalData.longitude,
      'circles[0].radius': app.globalData.radius,
      'polyline[0].points[1].latitude': app.globalData.latitude,
      'polyline[0].points[1].longitude': app.globalData.longitude,   
    })
    console.log(that.data.polyline[0].points[1].latitude);
    console.log(that.data.polyline[0].points[1].longitude);
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        that.setData({
          'polyline[0].points[0].latitude': latitude,
          'polyline[0].points[0].longitude': longitude,
          latitude: latitude,
          longitude: longitude,
        })
        var timestamp = Date.parse(new Date());
        var now = new Date(timestamp);
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();
        console.log(year + " " + month + " " + day);
        var nowhm = now.getHours() * 60 + now.getMinutes();
        wx.request({
          url: 'http://localhost:8080/signRecord?date=' + year + '-' + month + '-' + day + '&jobNumber=' + app.globalData.jobNumber,
          method: 'GET',
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            var array = res.data;
            that.setData({
              i: array.signIn,
              o: array.signOut,
            })
            var i = that.data.i;
            var o = that.data.o;
            var distance=that.getDistance(latitude,longitude,app.globalData.latitude,app.globalData.longitude);
            console.log(distance);
            if (i) {
              that.setData({
                disabled1: true,
                message1: "已签到"
              })
            }
            else if (nowhm < that.data.sit) {
              that.setData({
                disabled1: true,
                message1: "签到"
              })
            }
            else if (distance <= that.data.circles[0].radius &&nowhm >= that.data.sit && nowhm <= (that.data.sit + 60)) {
              that.setData({
                disabled1: false,
                message1: "签到"
              })
            }
            else if (distance > that.data.circles[0].radius && nowhm >= that.data.sit && nowhm <= (that.data.sit + 60)) {
              that.setData({
                disabled1: true,
                message1: "签到"
              })
            }
            else {
              that.setData({
                disabled1: true,
                message1: "未签到"
              })
            }
            if (o) {
              that.setData({
                disabled2: true,
                message2: "已签退"
              })
            }
            else if (nowhm < that.data.sot) {
              that.setData({
                disabled2: true,
                message2: "签退"
              })
            }
            else if (distance <= that.data.circles[0].radius&&nowhm >= that.data.sot && nowhm <= (that.data.sot + 60)) {
              that.setData({
                disabled2: false,
                message2: "签退"
              })
            }
            else if (distance > that.data.circles[0].radius && nowhm >= that.data.sot && nowhm <= (that.data.sot + 60)) {
              that.setData({
                disabled2: true,
                message2: "签退"
              })
            }
            else {
              that.setData({
                disabled2: true,
                message2: "未签退"
              })
            }
          }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getDistance: function (lat1, lng1, lat2, lng2) {
    lat1 = lat1 || 0;
    lng1 = lng1 || 0;
    lat2 = lat2 || 0;
    lng2 = lng2 || 0;

    var rad1 = lat1 * Math.PI / 180.0;
    var rad2 = lat2 * Math.PI / 180.0;
    var a = rad1 - rad2;
    var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
    var r = 6378137;
    var distance = r * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(rad1) * Math.cos(rad2) * Math.pow(Math.sin(b / 2), 2)));

    return distance;
  }
})
