const Router = require('vue-router')

const HomePage = () => import('../views/HomePage')
const DashBoard = () => import('../views/DashBoard')
const Test = () => import('../views/Test')
const Verify = () => import('../views/Verify')

const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/',
      redirect:'/home'
    },
    {
      path:'/home',
      component: HomePage
    },
    {
      path:'/home/:traceCode',
      component: HomePage
    },
    {
      path: '/data',
      component: DashBoard
    },
    {
      path: '/test',
      component: Test
    },
    {
      path:'*',
      redirect: '/home'
    },
    {
      path: '/verify',
      component: Verify
    }
  ]
});


export default router;
