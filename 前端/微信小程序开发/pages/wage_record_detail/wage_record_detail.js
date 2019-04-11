Page({
  data: {
    jobNumber:"",
    name:"",
    dates: "",
    index: 0,
  },
  //  点击日期组件确定事件  
  bindDateChange: function (e) {
    console.log(e.detail.value)
    this.setData({
      dates: e.detail.value
    })
  },
  onLoad: function (options) {
    var that = this;
    var jobNumber=options.jobNumber;
    var name=options.name;
    var month=options.month;
    var year=options.year;
    wx.request({
      url: 'http://localhost:8080/personalSalary?month=' + month + '&year=' + year+'&jobNumber='+jobNumber,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        var array = res.data;
        that.setData({
          jobNumber:jobNumber,
          name:name,
          dates: year + '-' + month,
          arr: array,
        })
      }
    })
    console.log("options+++++++++++", options)
  }    
})