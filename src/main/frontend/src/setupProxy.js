// src/main/frontend/src/setupProxy.js

const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
    app.use(
        // 여기서 /api로 시작하는 요청은 모두 localhost:8080 으로 간다.
        "/api",
        createProxyMiddleware({
            target: "http://localhost:8080",
            changeOrigin: true,

        })
    );
};
