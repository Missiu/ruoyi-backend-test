<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评委姓名" prop="judgeName">
        <el-input
          v-model="queryParams.judgeName"
          placeholder="评委"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作品" prop="workName">
        <el-input
          v-model="queryParams['activeWorks.workName']"
          placeholder="请输入作品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学校" prop="schoolName">
        <el-input
          v-model="queryParams['activeWorks.schoolName']"
          placeholder="请输入学校"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参赛人" prop="participant">
        <el-input
          v-model="queryParams['activeWorks.participant']"
          placeholder="请输入参赛姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名组别" prop="groupName">
        <el-select v-model="queryParams['activeWorks.groupName']" placeholder="请选择报名组别" clearable>
          <el-option
            v-for="dict in dict.type.active_group_name"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['active:evaluation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="evaluationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学校" align="center" prop="activeWorks.schoolName" />
      <el-table-column label="作品名称" align="center" prop="activeWorks.workName" />
      <el-table-column label="评委姓名" align="center" prop="judgeName" />
      <el-table-column label="教学实践" align="center" prop="scoreTeachingImplementation" />
      <el-table-column label="教案" align="center" prop="scoreLessonPlan" />
      <el-table-column label="视频材料" align="center" prop="scoreVideoMaterial" />
      <el-table-column label="专业人才培养方案" align="center" prop="scoreTalentTraining" />
      <el-table-column label="课程标准" align="center" prop="scoreCourseStandard" />
      <el-table-column label="教材合用" align="center" prop="scoreTeachingMaterial" />
      <el-table-column label="得分" align="center" prop="finalScore" />
      <el-table-column label="评分时间" align="center" prop="evaluatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.evaluatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['active:evaluation:edit']"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listEvaluation, getEvaluation, delEvaluation, addEvaluation, updateEvaluation } from "@/api/active/evaluation";

export default {
  name: "Evaluation",
  dicts: ['active_judge_scope', 'active_group_name', 'active_user_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 作品评价表格数据
      evaluationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workId: null,
        judgeName: null,
        'activeWorks.workName': null,
        'activeWorks.schoolName': null,
        'activeWorks.participant': null,
        'activeWorks.groupName': null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询作品评价列表 */
    getList() {
      this.loading = true;
      listEvaluation(this.queryParams).then(response => {
        this.evaluationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        evaId: null,
        workId: null,
        judgeName: null,
        scoreTeachingImplementation: null,
        scoreLessonPlan: null,
        scoreVideoMaterial: null,
        scoreTalentTraining: null,
        scoreCourseStandard: null,
        scoreTeachingMaterial: null,
        finalScore: null,
        evaluatedAt: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.evaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('active/evaluation/export', {
        ...this.queryParams
      }, `evaluation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
