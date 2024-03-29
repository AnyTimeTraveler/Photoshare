module.exports = {
    entry: [
        './src/index.jsx',
    ],
    mode: 'production',
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
};
