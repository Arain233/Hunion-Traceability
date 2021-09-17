import request from "@/utils/request";

// 开发环境
export function systemStatus() {
  return request({
    url: '/wecross/sys/systemStatus',
    type: 'get'
  })
}

export function routerStatus() {
  return request({
    url: '/wecross/sys/routerStatus',
    method: 'get'
  })
}

// 生产环境
// export function systemStatus() {
//   return request({
//     url: 'http://120.76.197.109:8250/sys/systemStatus',
//     type: 'get'
//   })
// }
//
// export function routerStatus() {
//   return request({
//     url: 'http://120.76.197.109:8250/sys/routerStatus',
//     method: 'get'
//   })
// }
