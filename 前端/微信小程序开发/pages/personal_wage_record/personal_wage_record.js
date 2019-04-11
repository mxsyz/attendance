const app=getApp();
Page({
  data: {
    dates: "",
    index: 0,
    arr:{},
  },
  //  点击日期组件确定事件  
  bindDateChange: function (e) {
    var that = this;
    console.log(e.detail.value);
    var array1=new Array();
    array1 = e.detail.value.split("-");
    that.setData({
      dates: e.detail.value
    })
    wx.request({
      url: 'http://localhost:8080/personalSalary?jobNumber='+app.globalData.jobNumber+'&year=' + array1[0] + '&month=' + array1[1],
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
    var that = this;
    var timestamp = Date.parse(new Date());
    var date = new Date(timestamp);
    var M = (date.getMonth() ==0? '12' : date.getMonth());
    M = (M < 10 ? '0' + M : M);
    var Y = (date.getMonth() == 12 ? date.getFullYear() - 1 : date.getFullYear());
    that.setData({
      dates: Y+'-'+M,
      now: Y + '-' + M,
    })
    wx.request({
      url: 'http://localhost:8080/personalSalary?jobNumber=' + app.globalData.jobNumber +'&year='+Y+'&month='+M,
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