import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
import userDetail from '@/components/userDetail'
import ticketSelect from '@/components/ticketSelect'
import test from '@/components/test'
import userUpdate from '@/components/userUpdate'
import userDelete from '@/components/userDelete'
import userForgetPwd from '@/components/userForgetPwd'
import financeMonitor from '@/components/financeMonitor'
import systemMonitor from '@/components/systemMonitor'
import ticketManagerSelect from '@/components/ticketManagerSelect'
import orderDetail from '@/components/orderDetail'
import tableExpand from '@/components/tableExpend'
import tableCross from '@/components/tableCross'
import ticketAdd from '@/components/ticketAdd'
import userRealName from '@/components/userRealName'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/test',
      name: 'test',
      component: test
    },
    {
      path: '/index',
      name: 'index',
      component: index
    },
    {
      path: '/userUpdate',
      name: 'userUpdate',
      component: userUpdate
    },
    {
      path: '/ticketSelect',
      name: 'ticketSelect',
      component: ticketSelect
    },
    {
      path: '/userDelete',
      name: 'userDelete',
      component: userDelete
    },

    {
      path: '/userDetail',
      name: 'userDetail',
      component: userDetail
    },
    {
      path: '/userForgetPwd',
      name: 'userForgetPwd',
      component: userForgetPwd
    },
    {
      path: '/orderDetail',
      name: 'orderDetail',
      component: orderDetail
    },
    {
      path: '/financeMonitor',
      name: 'financeMonitor',
      component: financeMonitor
    },
    {
      path: '/systemMonitor',
      name: 'systemMonitor',
      component: systemMonitor
    },
    {
      path: '/ticketManager',
      name: 'ticketManagerSelect',
      component: ticketManagerSelect
    },
    {
      path: '/tableExpand',
      name: 'tableExpand',
      component: tableExpand
    },
    {
      path: '/tableCross',
      name: 'tableCross',
      component: tableCross
    },
    {
      path: '/ticketAdd',
      name: 'ticketAdd',
      component: ticketAdd
    },
    {
      path: '/userRealName',
      name: 'userRealName',
      component: userRealName
    }
  ]
})

