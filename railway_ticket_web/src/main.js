import Vue from 'vue'
import vuex from 'vuex'
import iView from 'iview'  //  1  使用iview
import 'iview/dist/styles/iview.css'    //  2  使用 CSS
import VueResource from 'vue-resource'
import VueCookies from 'vue-cookies'
import axios from 'axios'
import lif from 'lib-flexible'
import App from './App'
import router from './router'

axios.defaults.withCredentials=true;//让ajax携带cookie
Vue.prototype.$axios = axios;
Vue.use(VueResource);
Vue.config.productionTip = false;
Vue.use(iView);  //  3
Vue.use(VueCookies);
Vue.use(lif);
Vue.use(vuex)




Vue.prototype.$goRoute = function (index) {
  this.$router.push(index)
}

new Vue({
  el: '#app',
  router,
  data: function(){
    return {
      nowTime: '',
      endTime:0,
      etime:'',
      uMail:'',
      id:'',
      pwd:'',
      //uorigin:'http://127.0.0.1:8085',
      name:'',
      //url:'http://127.0.0.1:8085',
	  url:'http://www.jiamsh.com:8085',
	  uorigin:'http://www.jiamsh.com:8085',
    }
  },
  components: { App },
  template: '<App/>'
})


