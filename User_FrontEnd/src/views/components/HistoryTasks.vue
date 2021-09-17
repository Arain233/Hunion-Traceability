<template>
  <common-card title="历史数据一览">
    <template v-slot:content>
      <v-chart :options='option' style="height:210px; width:100%;" :autoresize='true'/>
    </template>
  </common-card>
</template>

<script>
// @ is an alias to /src
import CommonCard from './CommonCard.vue'
import * as echarts from 'echarts'

export default {
  name: 'HistoryTasks',
  components: {
    CommonCard
  },
  data () {
    return {
      option: {
        color: ['#4a9df2'],
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: '15%',
          left: '3%',
          right: '4%',
          bottom: 0,
          containLabel: true
        },
        xAxis: [
          {
            data: ['7/19', '7/20', '7/21', '7/22', '7/23', '7/24', '7/25', '7/26', '7/27', '7/28', '7/29', '7/30', '7/31', '8/1',],
            axisLine: {
              lineStyle: {
                color: '#4e548b'
              }
            },
            axisLabel: {
              color: '#eee',
              fontFamily: 'Microsoft YaHei',
              fontSize: 10
            },
            axisTick: {
              alignWithLabel: true,
              length: 0
            }
          }
        ],
        yAxis: [
          {
            splitNumber: 6,
            type: 'value',
            axisLine: {
              show: false
            },
            axisLabel: {
              color: '#fefefe'
            },
            axisTick: {
              length: 0
            },
            splitLine: {
              lineStyle: {
                color: '#1f284a',
                type: 'dashed'
              }
            }
          }
        ],
        series: [
          {
            name: '商品溯源',
            type: 'line',
            data: [10, 52, 20, 34, 39, 33, 22, 22, 33, 52, 12, 66, 26],
            symbol: 'none',
            itemStyle: {
              color: '#4a9df2'
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(74, 157, 242, 0.8)'
              }, {
                offset: 1,
                color: 'rgba(0, 0, 0, 0)'
              }])
            },
          },
          {
            name: '商品入库',
            type: 'line',
            data: [12, 10, 12, 33, 30, 24, 22, 10, 17, 42, 34, 30, 14, 22, 25],
            symbol: 'none',
            itemStyle: {
              color: '#41f487'
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(65, 244, 135, 0.8)'
              }, {
                offset: 1,
                color: 'rgba(0, 0, 0, 0)'
              }])
            },
          }
        ],
        legend: {
          top: 0,
          itemWidth: 6,
          right: '4%',
          textStyle: {
            color: '#fff',
            fontSize: 10
          },
          icon: 'circle'
        }
      }
    }
  },
}
</script>

<style scoped lang='scss'>
</style>
