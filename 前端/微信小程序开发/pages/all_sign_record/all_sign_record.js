// pages/all_sign_record/all_sign_record.js
Page({
  data: {
    dates: "",
    index: 0,
    arr:{},
  },
  search: function (e) {
    var that = this;
    var val = e.detail.value.textinfo;
    wx.request({
      url: 'http://localhost:8080/searchSignRecord?date=' + that.data.dates+'&key='+val,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        var array = res.data;
        that.setData({
          arr: array,
        })
      }
    })
  },
  //  点击日期组件确定事件  
  bindDateChange: function (e) {
    var that=this;
    that.setData({
      dates: e.detail.value
    })
    wx.request({
      url: 'http://localhost:8080/allSignRecord?date=' + that.data.dates,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        var array = res.data;
        that.setData({
          arr: array,
        })
      }
    })
  },
  onLoad: function (e) {
    var that=this;
    var timestamp = Date.parse(new Date());
    var date = new Date(timestamp);
    var Y = date.getFullYear();
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    that.setData({
      dates: Y + '-' + M + '-' + D,
      now: Y + '-' + M + '-' + D,
    })
    wx.request({
      url: 'http://localhost:8080/allSignRecord?date='+that.data.dates,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        var array = res.data;
        that.setData({
          arr: array,
        })
      }
    })
  }
})