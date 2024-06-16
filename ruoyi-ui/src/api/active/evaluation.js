import request from '@/utils/request'

// 查询作品评价列表
export function listEvaluation(query) {
  return request({
    url: '/active/evaluation/list',
    method: 'get',
    params: query
  })
}

// 查询作品评价详细
export function getEvaluation(evaId) {
  return request({
    url: '/active/evaluation/' + evaId,
    method: 'get'
  })
}

// 新增作品评价
export function addEvaluation(data) {
  return request({
    url: '/active/evaluation',
    method: 'post',
    data: data
  })
}

// 修改作品评价
export function updateEvaluation(data) {
  return request({
    url: '/active/evaluation',
    method: 'put',
    data: data
  })
}

// 删除作品评价
export function delEvaluation(evaId) {
  return request({
    url: '/active/evaluation/' + evaId,
    method: 'delete'
  })
}
