const Mock = require('mockjs')
import request from "@/utils/request";

// 开发环境
export function listPeers() {
  return request({
    url: '/wecross/conn/listPeers',
    method: 'get',
  })
}

export function listChains(params) {
  return request({
    url: '/wecross/conn/listChains?zone=payment',
    method: 'get',
  })
}

export function listZones() {
  return request({
    url: '/wecross/conn/listZones',
    method: 'get',
  })
}


// 生产环境
// export function listPeers() {
//   return request({
//     url: 'http://120.76.197.109:8250/conn/listPeers',
//     method: 'get',
//   })
// }
//
// export function listChains(params) {
//   return request({
//     url: 'http://120.76.197.109:8250/conn/listChains?zone=payment',
//     method: 'get',
//   })
// }
//
// export function listZones() {
//   return request({
//     url: 'http://120.76.197.109:8250/conn/listZones',
//     method: 'get',
//   })
// }
