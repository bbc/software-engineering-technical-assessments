/* This allows Jest to run our JSX files natively, without getting confused by `import './foo.css'` imports */
module.exports = {
    process() {
        return 'module.exports = {};';
    }
};
