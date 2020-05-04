<template>
  <div class="app-container">
    <div class="filter-container">
      <el-checkbox-group v-model="checkboxVal">
        <el-checkbox label="apple">
          apple
        </el-checkbox>
        <el-checkbox label="banana">
          banana
        </el-checkbox>
        <el-checkbox label="orange">
          orange
        </el-checkbox>
      </el-checkbox-group>
    </div>
    <el-table
      :data="tableData"
      stripe
      v-loading="loginLoading"
      tooltip-effect="light"
      row-style="height:0"
      cell-style="padding:0"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column
        v-for="(data,index) in tableHeader"
        :show-overflow-tooltip="true"
        :key="index"
        :prop="data.prop"
        :label="data.label"
        :min-width="data['min-width']"
        :align="data.align">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        min-width="120">
        <template slot-scope="scope">
          <el-button type="text" size="mini"
                     :disabled="scope.row.serverStatus === '使用中'"
                     @click="applyServer(scope.row)">查看详情
          </el-button>
          <el-button type="text" size="mini"
                     :disabled="scope.row.serverStatus === '空闲'"
                     @click="releaseServer(scope.row.id)">当前行情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.pageIndex"
      :page-sizes="[20, 50, 100]"
      :page-size=pagination.pageSize
      layout="total, sizes, prev, pager, next, jumper"
      :total=pagination.total>
    </el-pagination>
  </div>
</template>

<script>
import {queryList} from "@/api/security";
import {formatDateTime, responseText, debounce} from "../../../utils/response.js";
const defaultFormThead = ['apple', 'banana']


export default {

  data() {
    return {
      queryType: '',
      queryKeyword: '',
      pagination: {
        pageIndex: 1,
        pageSize: 50,
        total: 0,
      },
      label: '证券基本信息',
      messageForm: {},
      messageVisible: false,
      messageLabelWidth: '90px',
      operate: '',
      multipleSelection: [],//多选的数据
      tableData: [],
      loginLoading: false,
      tableHeader: [
        {
          prop: 'securityId',
          label: 'securityCode',
          'min-width': 80,
          align: 'center',
        },
        {
          prop: 'exch',
          label: 'exch',
          'min-width': 40,
          align: 'center',
        },
        {
          prop: 'securityName',
          label: 'securityCN',
          'min-width': 120,
          align: 'center',
        },
        {
          prop: 'status',
          label: 'status',
          'min-width': 100,
          align: 'center',
        },
        {
          prop: 'industry',
          label: 'industry',
          'min-width': 120,
          align: 'center',
        },
        {
          prop: 'listDate',
          label: 'listDate',
          'min-width': 120,
          align: 'center',
        },
        {
          prop: 'marketType',
          label: 'marketType',
          'min-width': 120,
          align: 'center',
        },
      ]
    };
  },
  created() {
    this.queryList(this.pageIndex,this.pageSize);
  },
  methods: {
    queryList(page, pagesize) {
      queryList(page, pagesize).then(response => {
        console.log(response)
        this.tableData = responseText(response.result.records);
        this.pagination.total = response.result.records ? response.result.total : 0;
      }).catch(err => {
        console.log(err)
      })
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val;
      this.pagination.pageIndex = 1;
      this.queryList(this.pagination.pageIndex,this.pagination.pageSize);
    },
    handleCurrentChange(val) {
      this.pagination.pageIndex = val;
      this.queryList(this.pagination.pageIndex,this.pagination.pageSize);
    },
  },
  watch: {
    checkboxVal(valArr) {
      this.formThead = this.formTheadOptions.filter(i => valArr.indexOf(i) >= 0)
      this.key = this.key + 1// 为了保证table 每次都会重渲 In order to ensure the table will be re-rendered each time
    }
  }
}
</script>

