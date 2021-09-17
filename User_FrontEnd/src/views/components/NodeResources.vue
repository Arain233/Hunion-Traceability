<template>
  <common-card title="不同地市相关数据详情">
    <template v-slot:content>
      <tab-switcher @change="tabChange"/>
      <v-chart :options='option' style="height:160px; width:100%;margin-top: 15px;" :autoresize='true'/>
    </template>
  </common-card>
</template>

<script>
// @ is an alias to /src
import CommonCard from './CommonCard'
import * as echarts from 'echarts'
import TabSwitcher from './TabSwitcher'

export default {
  name: 'NodeResources',
  components: {
    CommonCard,
    TabSwitcher
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
          top: '5%',
          left: '3%',
          right: '4%',
          bottom: '12%',
          containLabel: true
        },
        xAxis: [
          {
            data: ['7/19', '7/20', '7/21', '7/22', '7/23', '7/24', '7/25', '7/26', '7/27', '7/28', '7/29', '7/30', '7/31', '8/1'],
            axisLine: {
              lineStyle: {
                color: '#4e548b'
              }
            },
            axisLabel: {
              rotate: 40,
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
            name: '浏览',
            type: 'line',
            data: [10, 52, 20, 34, 39, 33, 22, 22, 33, 22, 12, 26, 24],
            symbol: 'none',
            // sampling: 'average',
            smooth: true,
            itemStyle: {
              color: '#fff006'
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(255, 240, 6, 0.8)'
              }, {
                offset: 1,
                color: 'rgba(0, 0, 0, 0)'
              }])
            },
          },
          {
            name: '溯源',
            type: 'line',
            data: [12, 10, 12, 34, 33, 14, 22, 20, 10, 12, 24, 10, 23, 22, 25],
            symbol: 'none',
            smooth: true,
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
          },
          {
            name: '入库',
            type: 'line',
            data: [20, 10, 2, 14, 10, 4, 2, 5, 10, 12, 14, 13, 14, 22, 5],
            symbol: 'none',
            smooth: true,
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
        ],
        legend: {
          itemWidth: 6,
          bottom: -5,
          textStyle: {
            color: '#fff',
            fontSize: 10
          },
          icon: 'circle'
        }
      }
    }
  },
  methods: {
    tabChange(tab) {
      console.log(tab)
    }
  },
}
</script>

<style scoped lang='scss'>
</style>
