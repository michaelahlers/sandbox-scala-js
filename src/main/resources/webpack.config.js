var webpack = require('webpack');

var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = require('./scalajs.webpack.config');

module.exports.plugins = [
  new HtmlWebpackPlugin({title: 'Sandbox: Scala.js'})
];