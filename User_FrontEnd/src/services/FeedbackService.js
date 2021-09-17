import BaseService from './BaseService'

export default class FeedbackService {
  static create = (data, success) => {
    BaseService.jsonp('feedback/create', data, success)
  }
}
