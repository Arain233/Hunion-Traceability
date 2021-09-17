import request from "../utils/request";

export function getkey() {
  return request({
    url: '/api/pub/key/getkey',
    method: 'get',
  })
}

export function certificate(id) {
  return request({
    url: '/api/retailer/cert/' + id,
    method: 'get'
  })
}

export function trace(tra_code) {
  return request({
    url: '/api/pub/findByTra_code/' + tra_code,
    method: 'get',
  })
}
