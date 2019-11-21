<template>
  <div>
    <div class="tt33"><span style="padding-left: 119px;">铁路运营系统</span>
      <Button @click="" style="margin-top:1vh;float:right;border:none;font-size:14px;background: #57a3f3;color: white;">
        {{this.loginMessage}}
      </Button>
    </div>
    <div class="t2">
      <Form class="conjia" ref="form"  :rules="rule" inline :model="form">

        <FormItem label="出发地" style="width:280px;margin-left:35px;" prop="startSite">
          <Input type="text" style="width:210px;" v-model="form.startSite" placeholder="请输入出发地"/>
        </FormItem>
        <Button @click="take">
          <Icon size="20" type="ios-swap"/>
        </Button>

        <FormItem label="到达地" style="width:280px;margin-left:15px;margin-right: 100px;" prop="endSite">
          <Input type="text" style="width:210px;" v-model="form.endSite" placeholder="请输入到达地"/>
        </FormItem>
        <FormItem label="日期" style="width:280px;" prop="startDate">
          <Row>
            <Col span="12" style="width:200px;">
              <DatePicker class="ff5" style="width:200px;" v-model="form.startDatel" type="date"
                          @on-change="form.startDate=$event" format="yyyy-MM-dd" placeholder="请选择日期"></DatePicker>
            </Col>
          </Row>
        </FormItem>
        <br/>
        <FormItem label="" prop="status" style="width:500px;margin-left:80px;">
          <RadioGroup v-model="form.status">
            <Radio style="width:130px;" label='1'>售票数量最多</Radio>
            <Radio style="width:130px;" label='2'>售票金额最高</Radio>
          </RadioGroup>
        </FormItem>
      </Form>

      <div style="text-align: right;margin-right: 1%;">
        <Button type="primary" style="width:100px;" @click="back">返回</Button>
        <Button type="primary" style="width:100px;" @click="query">查询</Button>
      </div>
      <br/>
    </div>
    <div class="t3">
      <div class="t5">
        <Table border :columns="columns3" :data="data3"></Table>
        <br>
      </div>

    </div>
    <div class="t4">
      <Page :current.sync="currentPage" :total="dataCount" @on-change="change"/>
    </div>
    <div style="text-align: center;">
      <div class="foot">
        版权所有© 梅忠翔 王芸 周智慧
        <br/>2017-2019
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                currentPage: 1,
                status: 0,
                modalStatus: 0, //1 票价  2余量
                loginMessage: this.status == 0 ? "请登录" : "已登录",
                modal1: false,
                data3: [],
                dataCount: 10,
                columns3: [
                    {
                        title: '序号',
                        type: 'index',
                        align: 'center',
                        width: 97,
                    },
                    {
                        title: '车次',
                        width: 200,
                        key: 'trainNumber',
                        align: 'center',
                    },
                    {
                        title: '区间',
                        width: 330,
                        align: 'center',
                        key: 'site'
                    },
                    {
                        title: '出发时间',
                        width: 230,
                        align: 'center',
                        key: 'startTime'
                    },
                    {
                        title: '到达时间',
                        width: 230,
                        align: 'center',
                        key: 'endTime'
                    },
                    {
                        title: '售票数量',
                        width: 215,
                        align: 'center',
                        key: 'num'
                    },
                    {
                        title: '售票总额',
                        width: 215,
                        align: 'center',
                        key: 'price'
                    }
                ],
                form: {
                    startSite: '',
                    endSite: '',
                    startDate: '',
                    status: '',
                    startDatel: ''
                },
                rule: {
                    startSite : [
                        {required: true, message:"请输入出发地", trigger:'blur'}

                    ],
                    endSite : [
                        {required: true, message:"请输入到达地", trigger:'blur'}

                    ],
                    startDate : [
                        {required: true, message:"请输入日期", trigger:'blur'}
                    ]
                }
            }
        },
        created() {
            if (localStorage.getItem("managertatus") != '1') {
                this.$router.push("/ticketManager");
            } else {
                if (this.$route.query.startDate != '' && this.$route.query.startSite != '' && this.$route.query.endSite != '') {
                    this.form.startDate = this.$route.query.startDate;
                    this.form.startDatel = this.$route.query.startDate;
                    this.form.startSite = this.$route.query.startSite;
                    this.form.endSite = this.$route.query.endSite;
                    if(this.form.startSite != null && this.form.endSite != null && this.form.startDate != null){
                        this.cquery(this.form);
                    }
                }
            }
        },
        methods: {
            check() {
                if (this.status == 1) {
                } else {
                    this.modal1 = true
                }
            },
            modal2Ok() {
                if (this.mondalStatus == 1) {
                } else if (this.mondalStatus == 2) {
                } else {
                }
            },
            back() {
                this.$router.push({path: 'ticketManager'})
            },
            take() {
                let temp = this.form.startSite;
                this.form.startSite = this.form.endSite;
                this.form.endSite = temp;
            },
            change() {
                let parma = [];
                parma.startTime = this.form.startTime;
                parma.startSite = this.form.startSite;
                parma.endSite = this.form.endSite;
                parma.currentPage = this.currentPage;
                this.query(parma);
            },
            query() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let url = this.$root.url + "/proxy/4000001";
                        this.form.page = this.currentPage;
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
                                this.data3 = [];
                                this.data3 = data.row;
                                this.form.startDate = data.startDate;
                                this.dataCount = data.dataCount;
                            } else {
                                this.data3 = [];
                                this.dataCount = 10;
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
                            this.dataCount = 10;
                            this.$Modal.error({
                                title: '系统提示',
                                content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                                okText: '确认',
                                onOk: () => {
                                }
                            })
                        });
                    } else {
                        this.$Message.error('参数错误');
                    }
                })
            },
            cquery(parma) {
                let url = this.$root.url + "/proxy/4000001";
                let par = {};
                par.page = this.currentPage;
                par.startDate = parma.startDate;
                par.startSite = parma.startSite;
                par.endSite = parma.endSite;
                this.$axios({
                    url: url,
                    method: 'post',
                    data: parma,
                    responseType: 'json',
                    headers: {
                        "Access-Control-Allow-Origin": this.$root.uorigin,
                        "Access-Control-Allow-Credentials": true,
                        "withCredentials": true
                    }
                }).then(res => {
                    var data = res.data;
                    if (data.rspCode == "0000") {
                        this.data3 = [];
                        this.data3 = data.row;
                        this.form.startDate = data.startDate;
                        this.dataCount = data.dataCount;
                    } else {
                        this.data3 = [];
                        this.dataCount = 10;
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
                    this.dataCount = 10;
                    this.$Modal.error({
                        title: '系统提示',
                        content: '<p>' + "查询错误，请稍后重试！" + '</p>',
                        okText: '确认',
                        onOk: () => {
                        }
                    })
                });
            }
        }
    }
</script>

<style scoped>
  body {
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
    background-color: #57a3f3;
  }

  .t2 {
    margin-top: 100px;
  }

  .t3 {

  }

  .t4 {
    text-align: center;
    margin-bottom: 2vh;
  }

  .t5 {
    /*
      height:51vh;
    */
  }

  .t6 {
    width: 6vw;
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
    height: 13vh;
    text-align: center;
    text-decoration: none;
    background-color: #57a3f3;
  }

  .line {
    width: 90%;
    margin-left: 5%;
    text-align: center;
  }

  .tt11 {
    width: 360px;
  }


</style>
<!--
logo-yen-->
