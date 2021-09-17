const state = {
  pubkeyHex: '',
  prikeyHex: '',
  secretkey: '',
  pubkeyHexOfBack: ''
}
const mutations = {
  SET_PUBKEYHEX: (state, pubkeyHex) => {
    state.pubkeyHex = pubkeyHex
  },
  SET_PRIKEYHEX: (state, prikeyHex) => {
    state.prikeyHex = prikeyHex
  },
  SET_SECRETKEY: (state, secretkey) => {
    state.secretkey = secretkey
  },
  SET_PUBKEYHEXOFBACK: (state, pubkeyHexOfBack) => {
    state.pubkeyHexOfBack = pubkeyHexOfBack
  }
}
export default {
  namespace: true,
  state,
  mutations
}
