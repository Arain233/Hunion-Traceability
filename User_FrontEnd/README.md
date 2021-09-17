安装说明：
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

vue静态模板，没有后台，一套代码，pc端手机自适应，仅有主页，其他页面随后更新，更多的请查看

 **https://demo.hyxdsoft.com/** 

部署步骤
前期安装nodejs，vue
执行

npm install -g cnpm --registry=https://registry.npm.taobao.org
cnpm install
cnpm run dev
如果部署服务器出现404错误，修改
处理vue部署404错误
server {
listen 80;
server_name testwx.wangshibo.com;
root /Data/app/axe_ds/dist;## 自己的目录
index index.html;
access_log /var/log/testwx.log main;
## 注意从这里开始
location / {
try_files $uri KaTeX parse error: Expected 'EOF', got '}' at position 55: …html; }̲ locati… /index.html last;
}
}
![输入图片说明](https://images.gitee.com/uploads/images/2020/0514/123414_af706583_810825.png "搜狗截图20200514105808.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0514/123329_182b3cd7_810825.png "搜狗截图20200514105710.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0514/123248_a7b2c881_810825.png "搜狗截图20200514105648.png")