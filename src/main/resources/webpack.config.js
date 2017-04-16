var webpack = require('webpack');

var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = require('./scalajs.webpack.config');

Object.assign(module.exports, {
  plugins: [
    new HtmlWebpackPlugin({title: 'Sandbox: Scala.js'})
  ]
});

Object.assign(module.exports.output, {
  filename: '[name]-[hash].js',
  chunkFilename: '[id]-[hash].chunk.js'
});