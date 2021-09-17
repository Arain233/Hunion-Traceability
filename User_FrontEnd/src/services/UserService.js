import BaseService from './BaseService'

export default class UserService {
  static feedback = (data, success) => {
    BaseService.jsonp('user/feedback', data, success)
  }
}
