import request from '@/utils/request'

export function queryList(pageNo, pageSize) {
  return request({
    url: '/vue-element-admin/security/list',
    method: 'get',
    params: { pageNo, pageSize }
  })
}

