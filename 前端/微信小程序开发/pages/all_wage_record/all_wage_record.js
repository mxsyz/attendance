// pages/all_wage_record/all_wage_record.js
Page({
  data: {
    arr: {},
    dates: "",
    index: 0,
    searchValue:'',
  },
  //  点击日期组件确定事件  
  bindDateChange: function (e) {
    var that = this;
    that.setData({
      dates: e.detail.value
    })
    var array1 = new Array();
    array1 = e.detail.value.split("-");
    wx.request({
      url: 'http://localhost:8080/allSalaryRecord?month=' +array1[1] + '&year=' + array1[0],
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
  seeDetail: function(e) {
    var array1 = new Array();
    array1 = e.currentTarget.dataset.info.split(",");
    var array2=new Array();
    array2 = array1[2].split("-");
    wx.navigateTo({
      url: '../wage_record_detail/wage_record_detail?jobNumber='+array1[0]+'&year='+array2[0]+'&month='+array2[1]+'&name='+array1[1],
    })
  },
  search: function (e) {
    var that=this;
    var val = e.detail.value.textinfo;
    var dates=that.data.dates;
    var array1 = new Array();
    array1 = dates.split("-");
    wx.request({
      url: 'http://localhost:8080/searchSalaryRecord?month=' + array1[1] + '&year=' + array1[0]+'&key='+val,
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
    var M = (date.getMonth() == 0 ? '12' : date.getMonth());
    M = (M < 10 ? '0' + M : M);
    var Y = (date.getMonth() == 12 ? date.getFullYear() - 1 : date.getFullYear());
    that.setData({
      dates: Y + '-' + M,
      now: Y + '-' + M,
    })
    wx.request({
      url: 'http://localhost:8080/allSalaryRecord?month='+M+'&year='+Y,
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