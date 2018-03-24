const webpack = require('webpack');
const path = require('path');

module.exports = {
    entry: path.join(__dirname, 'src', 'index.js'),
    output: {
        path: path.join(__dirname, 'public'),
        filename: 'bundle.js',
    },
    devServer: {
        port: 8000,
        contentBase: path.join(__dirname, 'public'),
        publicPath: '/',
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, 'src'),
                loader: 'babel-loader',
                query: {
                    cacheDirectory: 'babel_cache',
                    presets: [ 'react', 'es2015', 'stage-2' ],
                },
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader',
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                loader: 'file-loader',
            },
            {
                test: /\.less$/,
                loaders: [ 'style-loader', 'css-loader', 'less-loader' ],
            },
            {
                test: /\.(jpg|jpeg|png|gif)$/,
                loader: 'file-loader',
            },
            {
                test: /\.svg$/,
                loader: 'svg-loader',
            },
        ],
    },
    devtool: 'source-map',
    plugins: [
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV),
        }),
        new webpack.optimize.OccurrenceOrderPlugin(),
    ],
};
