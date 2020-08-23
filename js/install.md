~~~
sudo apt-get install npm

sudo npm install -g cnpm --registry=https://registry.npm.taobao.org

npm config set registry http://registry.npm.taobao.org/

npm get registry

npm install chromedriver --chromedriver_cdnurl=http://cdn.npm.taobao.org/dist/chromedriver

sudo cnpm install -g @vue-cli

vue init webpack admin-pro

sudo cnpm i element-ui -S

sudo cnpm install axios -S

cnpm run dev

~~~

目录|Desc
-|-|
build|项目构建(webpack)相关代码
config|配置目录，包括端口号等。我们初学可以使用默认的。
node_modules|npm 加载的项目依赖模块
src|开发目录
assets|放置一些图片，如logo等。
components|目录里面放了一个组件文件，可以不用。
App.vue:|项目入口文件，我们也可以直接将组件写这里，而不使用 components 目录。
main.js|项目的核心文件。
static|静态资源目录，如图片、字体等。
test|初始测试目录，可删除
.xxxx文件|这些是一些配置文件，包括语法配置，git配置等。
index.html|首页入口文件，你可以添加一些 meta 信息或统计代码啥的。
package.json|项目配置文件。

~~~
//卸载
npm uninstall -g vue-cli

sudo npm install -g @vue/cli

sudo vue add element

sudo npm install axios
~~~

~~~
sudo cnpm install -g @vue/cli

sudo cnpm install -g @vue/cli-init

~~~
export PATH=.:${IDEA_JDK_64}:$PATH

export NODE_HOME=/opt/node-v12.17.0-linux-x64

export PATH=$PATH:.:${NODE_HOME}/bin:$PATH

export NODE_PATH=$NODE_HOME/lib/node_modules

sudo ln -s /opt/node-v12.17.0-linux-x64/bin/node  /usr/bin/node
sudo ln -s /opt/node-v12.17.0-linux-x64/lib/node  /usr/lib/node
sudo ln -s /opt/node-v12.17.0-linux-x64/bin/npm  /usr/bin/npm
