springboot 在 service 中获取请求、响应，除了直接从controller中传参，还有下面的方式

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        logger.info(">>>>>>>>>>>>>>>request uri: " + request.getRequestURI());
        logger.info(">>>>>>>>>>>>>>>response: " + response);

        // LocaleContextHolder获取locale
        Locale locale = LocaleContextHolder.getLocale();
        logger.info(">>>>>>>>>>>>>>>" + locale);

        // request 获取locale
        logger.info(">>>>>>>>>>>>>>>" + request.getLocale());