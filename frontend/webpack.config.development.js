const webpack = require('webpack');

module.exports = {
    entry: [
        'react-hot-loader/patch',
        './src/index.jsx',
    ],
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: [ 'babel-loader' ],
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
    resolve: {
        extensions: [ '*', '.js', '.jsx' ],
    },
    output: {
        path: `${__dirname}/public`,
        publicPath: '/',
        filename: 'bundle.js',
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
    ],
    devtool: 'source-map',
    devServer: {
        proxy: {
            '/graphql': 'http://localhost:8080/',
            '/graphiql': 'http://localhost:8080/',
            '/api': 'http://localhost:8080/',
        },
        disableHostCheck: true,
        host: '0.0.0.0',
        port: 8000,
        contentBase: './public',
        historyApiFallback: true,
        hot: true,
    },
};
