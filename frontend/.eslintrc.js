const ERROR = 2;
const WARN = 1;
const OFF = 0;

module.exports = {
    globals: {
        __BACKEND_URL__: true
    },
    env: {
        browser: true,
        es6: true,
        node: true,
    },
    parser: 'babel-eslint',
    parserOptions: {
        ecmaFeatures: {
            experimentalObjectRestSpread: true,
            jsx: true,
            impliedStrict: true,
        },
        sourceType: 'module',
    },
    plugins: [
        'react',
        'babel',
    ],
    settings: {
        react: {
            pragma: 'React',
        },
    },
    rules: {
        'no-await-in-loop': ERROR,
        'no-compare-neg-zero': ERROR,
        'no-cond-assign': ERROR,
        'no-console': WARN,
        'no-constant-condition': ERROR,
        'no-control-regex': ERROR,
        'no-debugger': ERROR,
        'no-dupe-args': ERROR,
        'no-dupe-keys': ERROR,
        'no-duplicate-case': ERROR,
        'no-empty': ERROR, // force user to fill in a block statement. Some comment is enough, but at least it forces the programmer to think about what he's doing.
        'no-empty-character-class': ERROR,
        'no-ex-assign': ERROR,
        'no-extra-boolean-cast': ERROR,
        'no-extra-parens': [ ERROR, 'all', {
            nestedBinaryExpressions: false,
            ignoreJSX: 'all',
        }],
        'no-extra-semi': ERROR,
        'no-func-assign': ERROR,
        'no-inner-declarations': ERROR,
        'no-invalid-regexp': ERROR,
        'no-irregular-whitespace': [ERROR, {
            skipStrings: true,
            skipComments: false,
            skipRegExps: true,
            skipTemplates: true,
        }],
        'no-obj-calls': ERROR,
        'no-unreachable': ERROR,
        'no-unsafe-finally': ERROR,
        'no-unsafe-negation': ERROR,
        'use-isnan': ERROR,
        'valid-typeof': ERROR,

        'accessor-pairs': ERROR,
        'array-callback-return': ERROR,
        'block-scoped-var': ERROR,
        'class-methods-use-this': [ ERROR, {
            exceptMethods: ['render', 'trash'],
        }],
        'complexity': [ERROR, {'max': 10}],
        'consistent-return': ERROR,
        'curly': [ ERROR, 'all' ],
        'default-case': ERROR,
        'dot-location': [ERROR, 'property'],
        'dot-notation': ERROR,
        // 'eqeqeq': [ERROR, 'always'],
        'guard-for-in': ERROR,
        'no-alert': ERROR,
        'no-caller': ERROR,
        'no-case-declarations': ERROR,
        'no-div-regex': ERROR,
        'no-else-return': ERROR,
        'no-empty-function': ERROR,
        'no-empty-pattern': ERROR,
        // 'no-eq-null': ERROR,
        'no-eval': ERROR,
        'no-extend-native': ERROR,
        'no-extra-bind': ERROR,
        'no-extra-label': ERROR,
        'no-fallthrough': ERROR,
        'no-floating-decimal': ERROR,
        'no-global-assign': ERROR,
        'no-implicit-coercion': ERROR, // !!obj is easier and better optimized than Boolean(something), same for all other options.
        'no-implicit-globals': ERROR,
        'no-implied-eval': ERROR,
        'no-invalid-this': ERROR,
        'no-iterator': ERROR,
        'no-labels': ERROR, // was here before I got here, no hard feelings either way
        'no-lone-blocks': ERROR,
        'no-loop-func': ERROR,
        'no-magic-numbers': OFF, // might want to be changed, but too convenient
        'no-multi-spaces': ERROR,
        'no-multi-str': ERROR,
        'no-new': ERROR,
        'no-new-func': ERROR,
        'no-new-wrappers': ERROR,
        'no-octal': ERROR,
        'no-octal-escape': ERROR,
        'no-param-reassign': ERROR,
        'no-proto': ERROR,
        'no-redeclare': ERROR,
        'no-restricted-properties': ERROR, // turn on when we have objects which we do not want direct property access to
        'no-return-assign': ERROR,
        'no-return-await': ERROR,
        'no-script-url': ERROR,
        'no-self-assign': ERROR,
        'no-self-compare': ERROR,
        'no-sequences': ERROR,
        'no-throw-literal': ERROR,
        'no-unmodified-loop-condition': ERROR,
        'no-unused-expressions': ERROR,
        'no-unused-labels': ERROR,

        'no-useless-call': ERROR,
        'no-useless-concat': ERROR,
        'no-useless-escape': ERROR,
        'no-useless-return': ERROR,
        'no-void': ERROR,
        'no-warning-comments': ERROR, // ERROR on "t|odo", "f|ixme" and "xxx" at the start of comments.
        'no-with': ERROR,
        'prefer-promise-reject-errors': ERROR,
        'radix': [ERROR, 'always'],
        'require-await': ERROR,
        'vars-on-top': ERROR, // too much of an hassle in bigger code blocks
        'wrap-iife': [ERROR, 'inside'],
        'yoda': ERROR,

        'init-declarations': ERROR,
        'no-catch-shadow': ERROR,
        'no-delete-var': ERROR,
        'no-label-var': ERROR,
        'no-shadow': ERROR,
        'no-shadow-restricted-names': ERROR,
        'no-undef': ERROR,
        'no-undef-init': ERROR,
        'no-undefined': ERROR,
        'no-use-before-define': ERROR,
        'no-unused-vars': ERROR,

        // Callbacks & node-style programming
        'callback-return': ERROR,
        'global-require': ERROR, // we don't use require as-is, but require imports on global level
        'handle-callback-err': ERROR, // we don't use node-style error handling, but if we do, this ERRORs
        'no-mixed-requires': ERROR, // don't mix requires and normal assignments
        'no-new-require': ERROR, // you may not try this
        'no-path-concat': ERROR, // file systems are different on every computer, let libraries handle path concatenation
        'no-process-env': ERROR, // production/development switches may appear only when explicitly allowed.
        'no-process-exit': ERROR, // impossible, but don't use this
        'no-restricted-modules': ERROR, // we don't use this
        'no-sync': ERROR, // NOPE

        // Stylistic things
        'array-bracket-spacing': [ ERROR, 'always', {
            arraysInArrays: false,
            objectsInArrays: false,
        }],
        'block-spacing': [ERROR, 'always'],
        'brace-style': [ERROR, '1tbs', {
            allowSingleLine: true,
        }],
        'camelcase': ERROR,
        'capitalized-comments': OFF,
        'comma-dangle': [ERROR, {
            arrays: 'always-multiline',
            objects: 'always-multiline',
            imports: 'only-multiline',
            exports: 'only-multiline',
            functions: 'ignore',
        }],
        'comma-spacing': [ERROR, {
            before: false,
            after: true,
        }],
        'comma-style': [ERROR, 'last'],
        'computed-property-spacing': [ERROR, 'never'],
        'consistent-this': ERROR,
        'eol-last': [ERROR, 'always'],
        'func-call-spacing': [ERROR, 'never'],
        'func-name-matching': ERROR,
        'func-names': [ ERROR, 'as-needed' ],
        'func-declarations': [OFF, 'declaration', { // This one doesn't work with 'export function something', apparently
            allowArrowFunctions: true,
        }],
        'id-blacklist': ERROR,
        'id-length': OFF,
        'id-match': ERROR,
        'indent': [ERROR, 4],
        'jsx-quotes': [ ERROR, 'prefer-single' ],
        'key-spacing': [ERROR, {
            beforeColon: false,
            afterColon: true,
            mode: 'minimum',
        }],
        'keyword-spacing': [ERROR, {
            before: true,
            after: true,
        }],
        'line-comment-position': ERROR,
        'linebreak-style': ERROR,
        'lines-around-comment': OFF,
        'lines-around-directive': [ ERROR, {
            before: 'always',
            after: 'always',
        }],
        'max-depth': ERROR,
        'max-len': [WARN, {
            code: 180,
            tabWidth: 4,
            ignoreComments: true,
        }],
        'max-lines': OFF,
        'max-nested-callbacks': [ OFF, 6 ],
        'max-params': [ OFF, {
            max: 5,
        }],
        'max-statements': OFF,
        'max-statements-per-line': [ERROR, {
            max: 2, // allow 'if (something) { statement }' inlines
        }],
        'multiline-terniary': OFF,
        'new-cap': [ERROR, {
            newIsCap: true,
            capIsNew: false,
            properties: false,
        }],
        'new-parens': ERROR,
        'newline-after-var': [ ERROR, 'always' ],
        'newline-before-return': OFF,
        'newline-per-chained-call': [ERROR, {
            ignoreChainWithDepth: 2,
        }],
        'no-array-constructor': ERROR,
        'no-bitwise': OFF,
        'no-continue': OFF,
        'no-inline-comments': OFF,
        'no-lonely-if': ERROR,
        'no-mixed-operators': ERROR,
        'no-multi-assign': ERROR,
        'no-multiple-empty-lines': [ ERROR, {
            max: 2,
            maxEOF: 1,
            maxBOF: 0,
        }],
        'no-negated-condition': OFF,
        'no-nested-ternary': OFF,
        'no-new-object': ERROR,
        'no-plusplus': ERROR,
        'no-restricted-syntax': ERROR,
        'no-tabs': ERROR,
        'no-ternary': OFF,
        'no-trailing-spaces': ERROR,
        'no-underscore-dangle': ERROR,
        'no-unneeded-ternary': ERROR,
        'no-whitespace-before-property': ERROR,
        'nonblock-statement-body-position': [ ERROR, 'below' ],
        'object-curly-newline': OFF,
        'object-curly-spacing': [ ERROR, 'always', {
            arraysInObjects: false,
            objectsInObjects: false,
        }],
        'object-property-newline': ERROR,
        'one-var': [ ERROR, 'never' ],
        'one-var-declaration-per-line': ERROR,
        'operator-assignment': [ ERROR, 'always' ],
        'operator-linebreak': [ ERROR, 'after' ],
        'padded-blocks': [ ERROR, 'never' ],
        'quote-props': [ ERROR, 'consistent-as-needed' ],
        'quotes': [ ERROR, 'single', {
            avoidEscape: true,
            allowTemplateLiterals: true,
        }],
        'require-jsdoc': ERROR,
        'semi': [ERROR, 'always'],
        'semi-spacing': [ERROR, {
            before: false,
            after: true,
        }],
        'sort-keys': OFF,
        'sort-vars': OFF,
        'space-before-blocks': [ ERROR, 'always' ],
        'space-before-function-paren': [ ERROR, {
            anonymous: 'never',
            named: 'never',
            asyncArrow: 'always',
        }],
        'space-in-parens': [ERROR, 'never'],
        'space-infix-ops': [ERROR, {
            int32Hint: false,
        }],
        'space-unary-ops': [ ERROR, {
            words: true,
            nonwords: false,
        }],
        'spaced-comment': [ ERROR, 'always' ],
        'template-tag-spacing': [ ERROR, 'never' ],
        'unicode-bom': [ ERROR, 'never' ],
        'wrap-regex': ERROR,

        // ES6+ features
        'arrow-body-style': [ ERROR, 'as-needed' ],
        'arrow-parens': [ ERROR, 'as-needed' ],
        'arrow-spacing': [ERROR, {
            before: true,
            after: true,
        }],
        'constructor-super': ERROR,
        'generator-star-spacing': [ERROR, {
            before: false,
            after: true,
        }],
        'no-class-assign': ERROR,
        'no-confusing-arrow': [ OFF, {
            allowParens: true,
        }],
        'no-const-assign': ERROR,
        'no-dupe-class-members': ERROR,
        'no-duplicate-imports': ERROR,
        'no-new-symbol': ERROR,
        'no-restricted-imports': ERROR,
        'no-this-before-super': ERROR,
        'no-useless-computed-key': ERROR,
        'no-useless-constructor': ERROR,
        'no-useless-rename': ERROR,
        'no-var': ERROR,
        'object-shorthand': [ ERROR, 'properties', {
            avoidQuotes: true,
        }],
        'prefer-arrow-callback': ERROR,
        'prefer-const': ERROR,
        'prefer-destructuring': [ ERROR, {
            object: true,
            array: false,
        }],
        'prefer-numeric-literals': ERROR,
        'prefer-rest-params': ERROR,
        'prefer-spread': ERROR,
        'prefer-template': ERROR,
        'require-yield': ERROR,
        'rest-spread-spacing': [ ERROR, 'never' ],
        'import-sorting': OFF,
        'symbol-description': ERROR,
        'template-curly-spacing': [ ERROR, 'never' ],
        'yield-star-spacing': [ERROR, 'after'],

        // React JSX rules
        'react/no-deprecated': ERROR,
        'react/no-did-mount-set-state': ERROR,
        'react/no-did-update-set-state': ERROR,
        'react/no-direct-mutation-state': ERROR,
        'react/no-is-mounted': ERROR,
        'react/no-multi-comp': ERROR,
        'react/no-render-return-value': ERROR,
        'react/no-string-refs': ERROR,
        'react/react-in-jsx-scope': ERROR,
        'react/require-render-return': ERROR,
        'react/self-closing-comp': ERROR,
        'react/style-prop-object': ERROR,

        'react/jsx-boolean-value': [ERROR, 'always'],
        'react/jsx-closing-bracket-location': [ OFF, 'tag-aligned' ],
        'react/jsx-curly-brace-presence': [ ERROR, {
            props: 'always',
            children: 'ignore',
        } ],
        'react/jsx-first-prop-new-line': [ERROR, 'multiline'],
        'react/jsx-indent': [ERROR, 4],
        'react/jsx-indent-props': [ERROR, 4],
        'react/jsx-key': ERROR,
        'react/jsx-max-props-per-line': [ ERROR, { 'maximum': 6 } ],
        'react/jsx-no-duplicate-props': ERROR,
        'react/jsx-no-undef': ERROR,
        'react/jsx-pascal-case': ERROR,
        'react/jsx-tag-spacing': [ ERROR, {
            closingSlash: 'never',
            afterOpening: 'never',
            beforeSelfClosing: 'never',
        }],

        // React compatibility
        'react/jsx-uses-react': ERROR,
        'react/jsx-uses-vars': ERROR,
    },
};
