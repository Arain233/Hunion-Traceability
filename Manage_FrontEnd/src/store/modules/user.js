import {login, logout, getInfo} from '@/api/user'
import {getToken, setToken, removeToken} from '@/utils/auth'
import router, {resetRouter} from '@/router'
import axios from 'axios'


const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  GET_TOKEN: state => state.token
}

const actions = {
  // user login
  /*login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        // const { data } = response // response对象从这里获得 换成axios获取
        commit('SET_TOKEN', data.token) // 获取response对象之后设置Token
        setToken(data.token)
        resolve()
      }).catch(error => {
        this.$message.error("登录错误！")
        reject(error)
      })
    })
  },*/

  // 4. 从@/src/views/login/index.vue跳转而来 拿到 loginForm -> userInfo 参数
  login({commit}, json) {
    // const {username, password, code} = userInfo // 把登录表解析到两个变量
    console.log(json);
    return new Promise((resolve, reject) => {
      axios.post('http://120.76.197.109:8282/api/login', {
        json: json
      }).then(response => {
        console.log(response.data)
        if (response.data.code === 200) {
          // 6. 如果返回200说明密码正确 然后获取后端返回的TOKEN 给store一个 给cookies一个
          commit('SET_TOKEN', response.data.data.token)
          setToken(response.data.data.token)
          console.log(state.token)
          console.log(getToken())
          // 7. 情况1: resolve Promise 返回到 @/src/views/login/index.js
          resolve()
        } else {
          // 如果不返回200 则一般是500 msg: "账号不存在/密码不正确"
          console.log('账号/密码不正确')
          // 7. 情况2: reject Promise 并返回axios获取到的错误信息 返回到 @/src/views/login/index.js
          reject(response.data.msg)
        }
      }).catch(error => {
        // 7. 情况3: catch axios error 然后reject Promise 返回到 @/src/views/login/index.js
        console.log('Status Code: 登录失败')
        console.log(error)
        reject()
      })
    })
  },

  // get user info
  // getInfo({ commit, state }) {
  //   return new Promise((resolve, reject) => {
  //     getInfo(state.token).then(response => {
  //       const { data } = response
  //
  //       if (!data) {
  //         reject('Verification failed, please Login again.')
  //       }
  //
  //       // const { roles, name, avatar, introduction } = data
  //       const {roles, name, introduction} = data
  //
  //       // roles must be a non-empty array
  //       if (!roles || roles.length <= 0) {
  //         reject('getInfo: roles must be a non-null array!')
  //       }
  //
  //       commit('SET_ROLES', roles)
  //       commit('SET_NAME', name)
  //       commit('SET_AVATAR', avatar) // 设置头像 先挂起 V2.0版本之后再做更新
  //       commit('SET_INTRODUCTION', introduction)
  //       resolve(data)
  //     }).catch(error => {
  //       reject(error)
  //     })
  //   })
  // },

  // 12. 从 @/src/permission.js -> beforeEach() 钩子函数跳转而来
  getInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      axios.get('http://120.76.197.109:8282/api/user/findByTkn', {
        headers: {
          token: state.token
        }
      }).then(response => {
        console.log(response)
        const data = response.data.data

        var roles
        if (data.role_id === 2) {
          roles = ['admin']
        } else {
          roles = ['user']
        }

        if (!data) {
          reject('Verification failed, please Login again.')
        }

        // const { roles, name, avatar, introduction } = data
        // const {roles, name, introduction} = data
        // const roles = ['admin']
        const name = data.name
        const introduction = ''
        const avatar = 'https://github.githubassets.com/images/modules/site/icons/footer/github-mark.svg'

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_ROLES', roles)
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar) // 设置头像 先挂起 V2.0版本之后再做更新
        commit('SET_INTRODUCTION', introduction)

        resolve({
          roles: roles,
          name: name,
          introduction: introduction,
          avatar: avatar
        })
      }).catch(error => {
        // reject 后进入 catch
        console.log('axios error:')
        console.log(error)
        // reject 后进入上层 catch 也就是
        reject(error)
      })
    })
  },


  // user logout
  logout({commit, state, dispatch}) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, {root: true})

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  async changeRoles({commit, dispatch}, role) {
    const token = role + '-token'

    commit('SET_TOKEN', token)
    setToken(token)

    const {roles} = await dispatch('getInfo')

    resetRouter()

    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', roles, {root: true})
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)

    // reset visited views and cached views
    dispatch('tagsView/delAllViews', null, {root: true})
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
