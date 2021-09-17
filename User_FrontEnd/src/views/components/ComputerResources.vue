<template>
  <common-card title="跨链事务资源调用情况">
    <template v-slot:content>
      <v-chart :options='option' style="height:220px; width:100%;" :autoresize='true'/>
    </template>
  </common-card>
</template>

<script>
// @ is an alias to /src
import CommonCard from './CommonCard.vue'
import * as echarts from 'echarts'

export default {
  name: 'ComputerResources',
  components: {
    CommonCard
  },
  data() {
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
            data: ['7/23', '7/24', '7/25', '7/26', '7/27', '7/28', '7/29', '7/30', '7/31', '8/1'],
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
            name: '调用数',
            type: 'line',
            data: [54, 11, 57, 37, 36, 38, 24, 54, 11, 54, 38, 33, 28, 24, 55],
            symbol: 'circle',
            itemStyle: {
              color: '#4a9df2'
            }
          },
          {
            name: '执行数',
            type: 'line',
            data: [52, 10, 52, 34, 33, 34, 22, 52, 10, 52, 34, 30, 24, 22, 50],
            symbol: 'circle',
            itemStyle: {
              color: '#29F2FF'
            }
          },
          {
            name: '回滚数',
            type: 'line',
            data: [2, 1, 5, 3, 3, 4, 2, 5, 1, 2, 4, 3, 4, 2, 5],
            symbol: 'circle',
            itemStyle: {
              color: '#fff006'
            }
          },
        ],
        legend: {
          itemWidth: 10,
          right: '4%',
          textStyle: {
            color: '#fff',
            fontSize: 14
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
