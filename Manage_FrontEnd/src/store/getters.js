const getters = {
  sidebar: state => state.app.sidebar,
  language: state => state.app.language,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  introduction: state => state.user.introduction,
  roles: state => state.user.roles,
  permission_routes: state => state.permission.routes,
  errorLogs: state => state.errorLog.logs,
  pubkeyHex: state => state.key.pubkeyHex,
  prikeyHex: state => state.key.prikeyHex,
  secretkey: state => state.key.secretkey,
  pubkeyHexOfBack: state => state.key.pubkeyHexOfBack,
  transactionID: state => state.transaction.transactionID,
  XAPaths: state => state.transaction.paths
}
export default getters
