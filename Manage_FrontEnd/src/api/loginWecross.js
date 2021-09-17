import request from "@/utils/request";
import {rsa_encode} from '@/utils/rsa';
import {confusePassword} from "@/utils/validate";

let username = 'org1-admin'
let password = '123456'

// async function getPub() {
//   var pub = ''
//   await request({
//     url: 'http://120.76.197.109:8250/auth/pub',
//     type: 'get',
//     async: false
//   }).then(response => {
//     pub =
//     console.log(pub)
//   })
//   return pub
// }


export function getAuthcode() {
  return request({
    url: 'http://120.76.197.109:8250/auth/authCode',
    type: 'get'
  })
}

function login(data) {
  return request({
    url: '/wecross/auth/login',
    method: 'post',
    data: {
      version: '1',
      data: data
    }
  })
}

export async function loginWecross(authCode, randomToken) {
  let confuse = confusePassword(password)
  let loginParams = {
    username,
    password: confuse,
    authCode,
    randomToken,
  }
  var rsaEncode = ''
  console.log(loginParams)
  await request({
    url: 'http://120.76.197.109:8250/auth/pub',
    type: 'get',
  }).then(response => {
    console.log(response.data.pub)
    rsaEncode = rsa_encode(JSON.stringify(loginParams), response.data.pub)
  })
  console.log(rsaEncode)
  login(rsaEncode).then(response => {
    console.log(response)
  })
}
