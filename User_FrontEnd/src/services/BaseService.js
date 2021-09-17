'use strict'
import jsonp from 'jsonp'
import store from '../store'

const buildQuery = (data) => {
  let query = ''
  for (let i in data) {
    let queryItem = ''
    if (Object.prototype.toString.call(data[i]) === '[object Array]') {
      queryItem = data[i].map((item, key) => {
        return i + '[' + key + ']=' + item
      }).join('&')
    } else {
      queryItem = i + '=' + encodeURIComponent(data[i])
    }
    query += queryItem + '&'
  }
  return query.substr(0, query.length - 1)
}

const buildUrl = (path, data) => {
  const protocol = location.href.indexOf('https') === -1 ? 'http://' : 'https://'
  const host = path.indexOf('//') === -1 ? protocol + process.env.API_HOST + '/' : ''
  const query = data ? '?' + buildQuery(data) : ''
  return host + path + query
}

const beforeSend = (data) => {
  if (data && data.loading === false) {
    store.commit('loading', false)
  } else {
    store.commit('loading', true)
    window.ajaxnum = (window.ajaxnum || 0) + 1
  }
}

const afterSend = (data) => {
  store.commit('loading', false)
  window.ajaxnum = (window.ajaxnum || 0) + 1
}

const onStatusError = (res) => {
  if (res.status === 40001) {
    store.commit('requireLogin')
    return false
  }
  store.commit('toast', res.message || '请求错误, 请稍候重试')
}

const onRequestError = (err) => {
  store.commit('requestError', err)
}

export default class BaseService {
  /**
   * send jsonp request
   * @param url
   * @param data
   * @param onSuccess
   * @param onError
   */
  static jsonp = (url, data, onSuccess, onError) => {
    beforeSend(data)
    jsonp(buildUrl(url, data), {
      timeout: 10000
    }, (err, res) => {
      afterSend(res)

      if (err != null) {
        onRequestError(err)
        return false
      }
      if (res.status !== 0 && res.status !== 40006 && res.status !== 80001) {
        onStatusError(res)
        return false
      }

      onSuccess && onSuccess(res)
    })
  }
}
