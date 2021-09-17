import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
// import componentsRouter from './modules/components'
import chartsRouter from './modules/charts'
// import tableRouter from './modules/table'
// import nestedRouter from './modules/nested'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','user']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/login/register'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/homepage/index'),
        name: 'Dashboard',
        meta: {title: 'dashboard', icon: 'dashboard', affix: true}
      }
    ]
  },



  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: {title: 'profile', icon: 'user', noCache: true}
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  /** when your routing map is too long, you can split it into small modules **/
  // componentsRouter,
  chartsRouter,
  // nestedRouter,
  // tableRouter,
  // {
  //   path: '/',
  //   component: Layout,
  //   redirect: '/home',
  //   children: [{
  //     path: 'home',
  //     name: 'homepage',
  //     component: () => import('@/views/homepage/index'),
  //     meta: { title: '平台首页', icon: 'dashboard' }
  //   }]
  // },
  {
    path: '/xaTransaction',
    component: Layout,
    name: 'XATransaction',
    redirect: 'transaction/xaTransactionList',
    children: [
      {
        name: 'xaTransaction',
        path: 'xaTransaction',
        hidden: true,
        component: () => import('@/views/transaction/xaTransaction'),
        meta: { title: '事务交易' }
      },
      {
        name: 'xaTransactionList',
        path: 'xaTransactionList',
        component: () => import('@/views/transaction/xaTransactionList'),
        meta: { title: '事务管理', icon: 'wecross' }
      }
    ]
  },
  {
    path: '/manipulation',
    component: Layout,
    redirect: '/manipulation/list',
    name: 'Manipulation',
    meta: {
      title: 'manipulation',
      icon: 'el-icon-s-help'
    },
    children: [
      {
        path: 'find',
        component: () => import('@/views/example/findByCode'),
        name: 'FindByCode',
        meta: {
          title: 'findByCode',
          icon: 'search',
          roles: ['admin', 'user']
        },
      },
      /*{
        path: 'in',
        component: () => import('@/views/example/in'),
        name: 'MedicineIn',
        meta: {
          title: 'wareHoseIn',
          icon: 'edit',
          roles: ['admin']
        },
      },*/
      {
        path: 'enterInfo',
        component: () => import('@/views/example/enterInfo'),
        name: 'enterInfo',
        redirect: '/manipulation/enterInfo/one',
        meta: {
          title: 'enterInfo',
          icon: 'edit',
          roles: ['admin']
        },
        children: [
          {
            path: 'one',
            component: () => import('@/views/example/one'),
            name: 'one',
            meta: {
              title: 'one',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
          {
            path: 'two',
            component: () => import('@/views/example/two'),
            name: 'two',
            meta: {
              title: 'two',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
          {
            path: 'three',
            component: () => import('@/views/example/three'),
            name: 'three',
            meta: {
              title: 'three',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
          {
            path: 'four',
            component: () => import('@/views/example/four'),
            name: 'four',
            meta: {
              title: 'four',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          }
        ]
      },
      {
        path: 'showInfo',
        component: () => import('@/views/example/showInfo'),
        name: 'showInfo',
        redirect: '/manipulation/showInfo/yuanliao',
        meta: {
          title: 'showInfo',
          icon: 'list',
          roles: ['admin']
        },
        children: [
          {
            path: 'yuanliao',
            component: () => import('@/views/example/list_yl'),
            name: 'yuanliao',
            meta: {
              title: 'yuanliao',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
          {
            path: 'shengchan',
            component: () => import('@/views/example/list_sc'),
            name: 'shengchan',
            meta: {
              title: 'shengchan',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
          {
            path: 'jingxiao',
            component: () => import('@/views/example/list_jx'),
            name: 'jingxiao',
            meta: {
              title: 'jingxiao',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
          {
            path: 'lingshou',
            component: () => import('@/views/example/list_ls'),
            name: 'lingshou',
            meta: {
              title: 'lingshou',
              icon: 'list',
              roles: ['admin']
            },
            hidden: true
          },
        ]
      }
    ]
  },
  {
    path: '/contactus',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Contactus',
        component: () => import('@/views/contactus/index'),
        meta: { title: '联系我们', icon: 'el-icon-notebook-2' }
        // 如果直接外跳路由的话就不需要组件和名称
        // path:'https://www.baidu.com',
      }
    ]
  },

  // {
  //   path: '/wecross',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'http://120.76.197.109:8250/s/index.html',
  //       name: 'Wecross',
  //       meta: {
  //         title: '跨链管理',
  //         icon: 'wecross',
  //         roles: ['admin']
  //       }
  //       // 如果直接外跳路由的话就不需要组件和名称
  //       // path:'https://www.baidu.com',
  //     }
  //   ]
  // },

  {
    path: '/explorer',
    component: Layout,
    name: 'explorer',
    meta: {
      title: 'explorer',
      roles: ['admin']
    },
    children: [
      {
        path: '',
        component: () => import('@/views/example/explorer'),
        name: 'explorer',
        meta: {
          title: 'explorer',
          icon: 'edit',
          roles: ['admin']
        },
      }
    ]
  },




  // 404 page must be placed at the end !!!
  {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
