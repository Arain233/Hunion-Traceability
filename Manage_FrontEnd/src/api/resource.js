const Mock = require('mockjs')
import request from "@/utils/request";

// 开发环境
export function getResourceList() {
  let data = {
    'version': '1',
    'data': {
      'ignoreRemote': 'false'
    }
  }
  return request({
    url: '/wecross/sys/listResources',
    method: 'post',
    data
  })
}

export function getResourceListByChain(params) {
  console.log(params)
  return request({
    url: '/wecross/sys/listResources',
    method: 'get',
    params
  })
}

// 生产环境
// export function getResourceList() {
//   let data = {
//     'version': '1',
//     'data': {
//       'ignoreRemote': 'false'
//     }
//   }
//   return request({
//     url: 'http://120.76.197.109:8250/sys/listResources',
//     method: 'post',
//     data
//   })
// }
//
// export function getResourceListByChain(params) {
//   console.log(params)
//   return request({
//     url: 'http://120.76.197.109:8250/sys/listResources',
//     method: 'get',
//     params
//   })
// }
