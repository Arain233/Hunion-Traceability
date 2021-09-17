import request from '@/utils/request'
import {path2Url} from '@/utils/oftenUse'

// 开发环境
export function startXATransaction(data) {
  return request({
    url: '/wecross/xa/startXATransaction',
    method: 'post',
    data: data
  })
}

export function commitXATransaction(data) {
  return request({
    url: '/wecross/xa/commitXATransaction',
    method: 'post',
    data: data
  })
}

export function rollbackXATransaction(data) {
  return request({
    url: '/wecross/xa/rollbackXATransaction',
    method: 'post',
    data: data
  })
}

export function getXATransaction(data) {
  return request({
    url: '/wecross/xa/getXATransaction',
    method: 'post',
    data: data
  })
}

export function listXATransactions(data) {
  return request({
    url: '/wecross/xa/listXATransactions',
    method: 'post',
    data: data
  })
}

export function call(data) {
  return request({
    url: /wecross/ + 'resource' + path2Url(data.path) + '/call',
    method: 'post',
    data: data
  })
}

export function sendTransaction(data) {
  return request({
    url: /wecross/ + 'resource' + path2Url(data.path) + '/sendTransaction',
    method: 'post',
    data: data
  })
}

export function listTransactions(params) {
  return request({
    url: '/wecross/trans/listTransactions',
    method: 'get',
    params: params
  })
}

export function getTransaction(params) {
  return request({
    url: '/wecross/trans/getTransaction',
    method: 'get',
    params: params
  })
}

//生产环境
// export function startXATransaction(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/xa/startXATransaction',
//     method: 'post',
//     data: data
//   })
// }
//
// export function commitXATransaction(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/xa/commitXATransaction',
//     method: 'post',
//     data: data
//   })
// }
//
// export function rollbackXATransaction(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/xa/rollbackXATransaction',
//     method: 'post',
//     data: data
//   })
// }
//
// export function getXATransaction(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/xa/getXATransaction',
//     method: 'post',
//     data: data
//   })
// }
//
// export function listXATransactions(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/xa/listXATransactions',
//     method: 'post',
//     data: data
//   })
// }
//
// export function call(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/' + 'resource' + path2Url(data.path) + '/call',
//     method: 'post',
//     data: data
//   })
// }
//
// export function sendTransaction(data) {
//   return request({
//     url: 'http://120.76.197.109:8250/' + 'resource' + path2Url(data.path) + '/sendTransaction',
//     method: 'post',
//     data: data
//   })
// }
//
// export function listTransactions(params) {
//   return request({
//     url: 'http://120.76.197.109:8250/trans/listTransactions',
//     method: 'get',
//     params: params
//   })
// }
//
// export function getTransaction(params) {
//   return request({
//     url: 'http://120.76.197.109:8250/trans/getTransaction',
//     method: 'get',
//     params: params
//   })
// }
