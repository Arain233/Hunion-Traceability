/**
 * 常用函数工具类
 */
'use strict'

const userAgent = navigator.userAgent
export default class Util {
  static os = {
    trident: userAgent.indexOf('Trident') > -1, // IE内核
    presto: userAgent.indexOf('Presto') > -1, // opera内核
    webKit: userAgent.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
    gecko: userAgent.indexOf('Gecko') > -1 && userAgent.indexOf('KHTML') === -1, // 火狐内核
    ios: !!userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
    android: userAgent.indexOf('Android') > -1 || userAgent.indexOf('Linux') > -1, // android终端或者uc浏览器
    iPhone: userAgent.indexOf('iPhone') > -1, // 是否为iPhone
    iPad: userAgent.indexOf('iPad') > -1, // 是否iPad
    webApp: userAgent.indexOf('Safari') === -1, // 是否web应该程序，没有头部与底部
    wechat: userAgent.indexOf('MicroMessenger') > -1
  }
}
