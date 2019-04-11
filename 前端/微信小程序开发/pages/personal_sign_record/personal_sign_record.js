// pages/sign_record/sign_record.js
const app=getApp();
Page({

  /**
   * 
   * 页面的初始数据
   */
  data: {
    dates: "",
    now:"",
    index: 0,
    arr:{},
  },
  bindDateChange: function (e) {
    var that = this;
    var array1 = new Array();
    array1 = e.detail.value.split("-");
    that.setData({
      dates: e.detail.value
    })
    wx.request({
      url: 'http://localhost:8080/personalSignRecord?jobNumber=' + app.globalData.jobNumber + '&month=' + that.data.dates,
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
  /** 
  * 弹出框蒙层截断touchmove事件 
  */
  preventTouchMove: function () {
  },
  /** 
   * 隐藏模态对话框 
   */
  hideModal() {
    var that = this;
    that.setData({
      showModal: true,
    })
  },
  showModalBtn() {
    var that = this;
    that.setData({
      showModal: false
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    var that = this;
    var timestamp = Date.parse(new Date());
    var date = new Date(timestamp);
    var Y = date.getFullYear();
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    that.setData({
      dates: Y + '-' + M,
      now:Y+'-'+M
    })   
    wx.request({
      url: 'http://localhost:8080/personalSignRecord?jobNumber='+app.globalData.jobNumber+'&month=' + that.data.dates,
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
})