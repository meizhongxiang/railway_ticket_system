<template>
  <div>
    <div class="tt33"><span style="padding-left: 100px;">系统监控</span>
      <Button @click="check" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">已登录<!--{{this.loginMessage}}--></Button>
       </div>
      <div class="t2">
        <Form class="conjia" ref="form" :rules="rule" inline :model="form">
          <FormItem label="开始时间" style="width:280px;margin-left:130px;" prop="thisDate">
            <Row >
              <Col span="12"  style="width:200px;">
                <DatePicker type="datetime" @on-change="form.thisDate=$event" format="yyyy-MM-dd HH:mm:ss" placeholder="请选择日期"></DatePicker>
              </Col>
            </Row>
          </FormItem>
          <FormItem label="终止时间" style="width:280px;" prop="thiseDate">
            <Row >
              <Col span="12"  style="width:200px;">
                <DatePicker type="datetime" @on-change="form.thiseDate=$event"  format="yyyy-MM-dd HH:mm:ss" placeholder="请选择日期"></DatePicker>
              </Col>
            </Row>
          </FormItem>
          <FormItem label="状态" prop="status" style="width:500px;margin-left:100px;">
            <RadioGroup v-model="form.status">
              <Radio  label='1'>正常</Radio>
              <Radio  label='2'>异常</Radio>
            </RadioGroup>
          </FormItem>
        </Form>
        <Modal v-model="modal1" :closable="false" :mask-closable="false" width="400">
          <p slot="header" style="color:#f60;">
          </p>
          <Row :gutter="32">
            <Col span="24" class="demo-tabs-style1">
              <Tabs>
                <TabPane label="登录">
                  <Form ref="login" :model="login" :rules="ruleInline" class="signin" style="margin-top:30px">
                    <FormItem prop="accountId">
                      <Input type="text"  class="aa11" prefix="ios-person-outline" v-model="login.accountId" placeholder="输入邮箱">
                      </Input>
                      <p class="error-text" v-show="login.error.accountId">{{login.error.accountId}}</p>
                    </FormItem>
                    <FormItem prop="userPassword">
                      <Input type="password" class="aa11" prefix="ios-lock-outline" v-model="login.userPassword" placeholder="输入密码">
                      </Input>
                      <p class="error-text" v-show="login.error.userPassword">{{login.error.userPassword}}</p>
                    </FormItem>

                    <FormItem style="text-align:center"  class="aa11">
                      <p class="error-text" v-show="login.error.all">{{login.error.all}}</p>
                      <Button type="primary" @click="loginSubmit" style="width:60%;font-size:16px">登录</Button>
                    </FormItem>
                  </Form>
                </TabPane>
              </Tabs>
            </Col>
          </Row>
          <div slot="footer" style="text-align:center">
            <p></p>
          </div>
        </Modal>
        <div style="text-align: right;margin-right: 1%;">
          <Button type="primary" style="width:100px;" @click="back">返回</Button>
          <Button type="primary" style="width:100px;" @click="query">查询</Button>
        </div><br />
      </div>
      <div class="t3">
        <div class="t5">
          <Table  border  :columns="columns3" :data="data3"></Table>
          <br>
        </div>

      </div>
    <div class="t4">
      <Page :current.sync="currentPage" :total="dataCount" @on-change="change"/>
    </div>
      <div style="text-align: center;">
        <div class="foot">
          版权所有© 梅忠翔 王芸 周智慧
          <br />2017-2019
        </div>
      </div>
  </div>
</template>

<script>
    import expandRow from './tableExpend';
    export default {
        components: { expandRow },
        data() {
            return {
                currentPage:1,
                payPwd:'',
                mStatus:0,
                loginMessage:"未登录",
                modal1: false,
                modal2:false,
                mondal2Value:'',
                data3:[
                ],
                dataCount:10,
                columns3: [

                    {
                        title: '请求IP',
                        key: 'requestIp',
                        align: 'center',
                        width: 90,
                    },
                    {
                        title: '业务名称',
                        key: 'description',
                        align: 'center',
                        width: 170
                    },
                    {
                        title: '业务处理类',
                        key: 'classPath',
                        align: 'center',
                        width: 400
                    },
                    {
                        title: '请求路径',
                        width: 150,
                        align: 'center',
                        key: 'actionUrl'
                    },
                    {
                        title: '请求方法',
                        key: 'requestMethod',
                        align: 'center',
                        width: 90
                    },
                    {
                        title: '业务详情',
                        type: 'expand',
                        width: 100,
                        render: (h, params) => {
                            return h(expandRow, {
                                props: {
                                    row: params.row
                                }
                            })
                        }
                    },
                    {
                        title: '请求开始时间',
                        width: 150,
                        align: 'center',
                        key: 'startTime'
                    },
                    {
                        title: '请求结束时间',
                        width: 150,
                        align: 'center',
                        key: 'finishTime'
                    },
                    {
                        title: '响应时长',
                        width: 130,
                        align: 'center',
                        key: 'consumingTime'
                    },
                    {
                        title: '系统状态',
                        width: 90,
                        align: 'center',
                        key: 'stype',
                        render: (h, params) => {
                            return h('div', [
                                params.row.stype==1?'正常':'异常'
                            ]);
                        }
                    }
                ],
                form: {
                    thisDate:'',
                    status:'',
                    thiseDate:''
                },
                rule: {
                    thisDate : [
                        {required: true, message:"请输入开始时间", trigger:'blur'}

                    ],
                    thiseDate : [
                        {required: true, message:"请输入结束时间", trigger:'blur'}

                    ]
                },
                login: {
                    accountId: '',
                    userPassword: '',
                    code:'',
                    error: {
                        accountId: false,
                        userPassword: false,
                        all: false
                    }
                },
                ruleInline: {
                    accountId : [
                        {
                            required: true,
                            message: '请输入用户名',
                            trigger: 'blur'
                        }
                    ],
                    userPassword : [
                        {
                            required: true,
                            message: '请输入密码',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },
        created() {
            if(localStorage.getItem("managertatus") != '1') {
                this.$router.push("/ticketManager");
            }else{
                this.loginMessage="退出";
                let params = {thisDate:this.from.thisDate, thiseDate:this.from.thiseDate, page:1};
                this.change(params);
            }
        },
        methods:{
            back() {
                this.$router.push({path: 'ticketManager'})
            },
            loginSubmit() {
                this.$refs['login'].validate((valid) => {
                    if (valid) {
                        let url = this.$root.url + "/proxy/2000006";
                        var params = this.login;
                        this.$axios({
                            url: url,
                            method: 'post',
                            data: params,
                            responseType: 'json',
                            headers: {
                                "Access-Control-Allow-Origin": this.$root.uorigin,
                                "Access-Control-Allow-Credentials": true,
                                "withCredentials": true
                            }
                        }).then(res => {
                            var data = res.data;
                            if (data.rspCode == "0000") {
                                localStorage.setItem("systatus", '1');
                                this.$Message.success(data.rspDesc);
                                this.modal1 = false;
                                this.loginMessage="退出";
                                this.change();
                            } else {
                                this.$Message.error(data.rspDesc);
                            }
                        }).catch(err => {
                            this.$Message.error('登录失败');
                        });
                    } else {
                        this.$Message.error('登录验证未通过!')
                    }
                })
            },

            check(){
                    if(localStorage.getItem("systatus") == '1'){
                        this.data3=[];
                        this.dataCount=10;
                        this.login.accountId= '';
                        this.login.userPassword= '';
                        localStorage.clear();
                        this.$Message.success('退出成功');
                        this.modal1 = true
                    }else{
                        this.modal1 = true
                    }
            },
            change(){
                let parma=[];
                parma.thisDate = this.form.thisDate;
                parma.thiseDate = this.form.thiseDate;
                parma.status = this.form.status;
                parma.currentPage = this.currentPage;
                this.query();
            },
            //query(parma){
            query(){
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let url = this.$root.url + "/proxy/5000001";
                        //let params = {thisDate:parma.thisDate, thiseDate:parma.thiseDate, page:parma.currentPage, status:parma.status};
                        this.form.page=this.currentPage;
                        this.$axios({
                            url: url,
                            method: 'post',
                            data: this.form,
                            responseType: 'json',
                            headers: {
                                "Access-Control-Allow-Origin": this.$root.uorigin,
                                "Access-Control-Allow-Credentials": true,
                                "withCredentials": true
                            }
                        }).then(res => {
                            var data = res.data;
                            if (data.rspCode == "0000") {
                                this.data3 = data.row;
                                this.dataCount = data.dataCount;
                            } else {
                                this.data3 = [];
                                this.dataCount=10;
                                this.$Modal.error({
                                    title: '系统提示',
                                    content: '<p>' + data.rspDesc + '</p>',
                                    okText: '确认',
                                    onOk: () => {
                                    }
                                })
                            }
                        }).catch(err => {
                            this.data3 = [];
                            this.dataCount=10;
                            this.$Modal.error({
                                title: '系统提示',
                                content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                                okText: '确认',
                                onOk: () => {
                                }
                            })
                        });
                    }})
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
.tt33 {
    width: 99vw;
    height: 50px;
    font-size: 23pt;
    color: white;
    display: inline-block;
    text-align: center;
    background-color:#57a3f3;
  }
.t2{
  margin-top: 100px;
}
.t3{

}
.t4{
  text-align: center;
  margin-bottom: 2vh;
}
.t5{
/*
  height:51vh;
*/
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

  .line {
    width: 90%;
    margin-left: 5%;
    text-align: center;
  }
.tt11{
  width:360px;
}
.ivu-table .demo-table-info-row td{
  background-color: #2db7f5;
  color: #fff;
}
.ivu-table .demo-table-error-row td{
  background-color: #ff6600;
  color: #fff;
}
.ivu-table td.demo-table-info-column{
  background-color: #2db7f5;
  color: #fff;
}
.ivu-table .demo-table-info-cell-name {
  background-color: #2db7f5;
  color: #fff;
}
.ivu-table .demo-table-info-cell-age {
  background-color: #ff6600;
  color: #fff;
}
.ivu-table .demo-table-info-cell-address {
  background-color: #187;
  color: #fff;
}

</style>
<!--
logo-yen-->
