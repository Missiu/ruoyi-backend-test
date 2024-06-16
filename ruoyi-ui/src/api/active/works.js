import request from '@/utils/request'

// 查询作品列表
export function listWorks(query) {
  return request({
    url: '/active/works/list',
    method: 'get',
    params: query
  })
}

// 查询作品详细
export function getWorks(workId) {
  return request({
    url: '/active/works/' + workId,
    method: 'get'
  })
}

// 新增作品
export function addWorks(data) {
  return request({
    url: '/active/works',
    method: 'post',
    data: data
  })
}

// 修改作品
export function updateWorks(data) {
  return request({
    url: '/active/works',
    method: 'put',
    data: data
  })
}

// 删除作品
export function delWorks(workId) {
  return request({
    url: '/active/works/' + workId,
    method: 'delete'
  })
}
