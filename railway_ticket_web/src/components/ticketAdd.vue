<template>
  <div>
    <div class="t1">
      <div class="ff1"><span style="padding-left: 119px;">铁路客票管理系统</span>
        <Button @click="check" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">{{this.loginMessage}}</Button></div>
      <div class="ff3">
        <p style="text-align: left;color:#57a3f3;font-size:13pt;">车次注册</p>
        <Form ref="form"  :rules="rule" inline :model="form">
          <div class="ff2">
            <FormItem label="车次：" class="ff6"  prop="trainNumber">
              <Input type="text" class="ff5" style="left:25px;" v-model="form.trainNumber" placeholder="请输入车次"/>
            </FormItem>
            <FormItem label="用时：" class="ff6"  prop="useTime">
              <Input type="text" class="ff5" style="left:25px;" v-model="form.useTime" placeholder="请输入运行时间"/>
            </FormItem>
            <FormItem label="出发地：" class="ff6" prop="startSite">
              <Input type="text" class="ff5" style="left:11px;" v-model="form.startSite" placeholder="请输入出发地"/>
            </FormItem>
            <FormItem label="到达地：" class="ff6" prop="endSite">
              <Input type="text" class="ff5" style="left:11px;" v-model="form.endSite" placeholder="请输入到达地"/>
            </FormItem>
            <FormItem label="出发时间：" class="ff6" prop="startTime">
              <Row >
                <Col span="12"  >
                  <DatePicker class="ff5" type="time" @on-change="form.startTime=$event" format="HH:mm:ss" placeholder="请选择时间"></DatePicker>
                </Col>
              </Row>
            </FormItem>
            <FormItem label="到达时间：" class="ff6" prop="endTime">
              <Row >
                <Col span="12"  >
                  <DatePicker class="ff5" type="time" @on-change="form.endTime=$event"  format="HH:mm:ss" placeholder="请选择时间"></DatePicker>
                </Col>
              </Row>
            </FormItem>
            <FormItem label="一等票量：" class="ff6" prop="classOne">
              <Input type="text" class="ff5" v-model="form.classOne" placeholder="请输入一等票量"/>
            </FormItem>
            <FormItem label="二等票量：" class="ff6" prop="classTwo">
              <Input type="text" class="ff5" v-model="form.classTwo" placeholder="请输入二等票量"/>
            </FormItem>
            <FormItem label="一等价格：" class="ff6" prop="priceOne">
              <Input type="text" class="ff5" v-model="form.priceOne" placeholder="请输入一等价格"/>
            </FormItem>
            <FormItem label="二等价格：" class="ff6" prop="priceTwo">
              <Input type="text" class="ff5" v-model="form.priceTwo" placeholder="请输入二等价格"/>
            </FormItem>
            <FormItem label="出发日期：" class="ff6" prop="startDate">
              <Row >
                <Col span="12"  >
                  <DatePicker class="ff5" type="date" @on-change="form.startDate=$event"  format="yyyy-MM-dd" placeholder="请选择日期"></DatePicker>
                </Col>
              </Row>
            </FormItem>
          </div>

        </Form>
        <div style="text-align: center;">

          <Button style="width:100px;" type="default" @click="back"> 返回</Button>
          <Button style="width:100px;" type="primary" @click="handleSubmit">确定</Button>
           </div><br /><br />
    </div>
      <div style="text-align: center;">
        <div class="foot">
          版权所有© 梅忠翔 王芸 周智慧
          <br />2017-2019
        </div>
      </div>
  </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                status:1,
                loginMessage:"已登录",
                form: {
                    trainNumber:'',
                    startSite:'',
                    endSite:'',
                    startTime:'',
                    endTime:'',
                    classOne:'',
                    classTwo:'',
                    priceOne:'',
                    priceTwo:'',
                    useTime:'',
                    startDate:''
                },
                rule: {
                    trainNumber : [
                        { required: true, message: '请输入车次！', trigger: 'blur' }
                    ],
                    startSite : [
                        {required: true, message:"请输入出发地", trigger:'blur'}

                    ],
                    endSite : [
                        {required: true, message:"请输入到达地", trigger:'blur'}

                    ],
                    startTime : [
                        {required: true, message:"请输入出发日期", trigger:'blur'}

                    ],
                    endTime : [
                        {required: true, message:"请输入到达日期", trigger:'blur'}

                    ],
                    classOne : [
                        {required: true,min:1, max:3,type: 'string',pattern:/^([1-9][0-9]*)$/, message:"请输入一等票量", trigger:'blur'}
                    ],
                    classTwo : [
                        {required: true,min:1, max:3,type: 'string',pattern:/^([1-9][0-9]*)$/, message:"请输入二等票量", trigger:'blur'}

                    ],
                    priceOne : [
                        {required: true,min:1, max:4,type: 'string',pattern:/^([1-9][0-9]*)$/, message:"请输入一等价格", trigger:'blur'}

                    ],
                    priceTwo : [
                        {required: true,min:1, max:4,type: 'string',pattern:/^([1-9][0-9]*)$/, message:"请输入二等价格", trigger:'blur'}

                    ],
                    useTime : [
                        {required: true, message:"请输入途经地", trigger:'blur'}
                    ],
                    startDate : [
                        {required: true, message:"请输入日期", trigger:'blur'}
                    ]
                }
            }
        },
        created() {
            if(localStorage.getItem("managertatus") == '1'){
            }else{
                this.$router.push("/ticketManager");
            }
        },
        methods:{
            back() {
                this.$router.push({path: 'ticketManager'})
            },
            handleSubmit(){
                    this.$refs['form'].validate(valid => {
                        if (valid) {
                            var url=this.$root.url+'/proxy/2000001';
                            var param= this.form;
                                this.$axios({
                                    url: url,
                                    method: 'post',
                                    data: param,
                                    responseType:'json',
                                    withCredentials:true,
                                    headers:{
                                        "Access-Control-Allow-Origin":this.$root.uorigin,
                                        "Access-Control-Allow-Credentials":true,
                                    }
                                }).then(res => {
                                    var data = res.data;
                                    if(data.rspCode == "0000"){
                                        this.$Modal.success({
                                            title: '系统提示',
                                            content: '<p>' + "添加车次成功" +'</p>',
                                            okText: '确认',
                                            onOk: () => {
                                                this.$router.push("/ticketManager");
                                            }
                                        })
                                    }else{
                                        this.$Modal.error({
                                            title: '系统提示',
                                            content: '<p>' + data.rspDesc + '</p>',
                                            okText: '确认',
                                            onOk: () => {
                                            }
                                        })
                                    }
                                }).catch(err => {
                                    this.$Modal.error({
                                        title: '系统提示',
                                        content: '<p>'+ "网络错误，请稍后重试！" +'</p>',
                                        okText: '确认',
                                        onOk: () => {
                                            this.$router.push("/ticketManager");
                                        }
                                    })
                                });
                        } else {
                            this.$Message.error('参数错误!');
                        }
                    });
            },
            handleReset(){
                this.$refs['form'].resetFields();
            }
        }
    }
</script>

<style scoped>
body{
}

  .listyle .al {
    display: block;
    padding: 50px;
    color: white;
    background-color: #8ce6b0;
    border-radius: 15px;
  }
.ff3{
  text-align: center;
  margin-bottom: 13vh;
}
.t6{
  width:6vw;
  font-size: 12pt;
  color: white;
  background-color: #57a3f3;
  border: none;
  float: right;
  padding-top: 1vh;
}

  .foot {
    padding-top: 3vh;
    color: white;
    width: 100%;
    height:13vh;
    text-align: center;
    text-decoration: none;
    background-color:#57a3f3;
  }

.userAdd .textare{
  width:480px;
}
.userAdd .f-single .ivu-form  .ivu-form-item  .ivu-form-item-label {
  width: 180px;
}
.userAdd .f-single .ivu-form  .ivu-form-item  .ivu-form-item-content {
  margin-left: 180px;
}

.ff1{
  width: 99vw;
  height: 50px;
  font-size: 23pt;
  color: white;
  display: inline-block;
  padding-left: 1%;
  text-align: center;
  background-color:#57a3f3;
}
.t1{
/*
  height: 100%;
*/
}
.ff2{
  margin-left: 28vw;
  margin-top: 10vh;
  width:50vw;
  text-align: left;
}
.ff5{
  width:210px;
}
  .ff6{
    width:320px;
  }

</style>
