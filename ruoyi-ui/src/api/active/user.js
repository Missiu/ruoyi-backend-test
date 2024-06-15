import request from '@/utils/request'

// 查询账号列表
export function listUser(query) {
  return request({
    url: '/active/user/list',
    method: 'get',
    params: query
  })
}

// 查询账号详细
export function getUser(userId) {
  return request({
    url: '/active/user/' + userId,
    method: 'get'
  })
}

// 新增账号
export function addUser(data) {
  return request({
    url: '/active/user',
    method: 'post',
    data: data
  })
}

// 修改账号
export function updateUser(data) {
  return request({
    url: '/active/user',
    method: 'put',
    data: data
  })
}

// 删除账号
export function delUser(userId) {
  return request({
    url: '/active/user/' + userId,
    method: 'delete'
  })
}
