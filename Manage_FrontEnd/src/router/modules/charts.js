/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const chartsRouter = {
  path: '/charts',
  component: Layout,
  name: 'Charts',
  children: [
    {
      path: '',
      component: () => import('@/views/table/drag-table'),
      name: 'charts',
      meta: {
        title: 'charts',
        icon: 'chart',
        roles: ['admin']
      },
    }
  ]
}

export default chartsRouter
