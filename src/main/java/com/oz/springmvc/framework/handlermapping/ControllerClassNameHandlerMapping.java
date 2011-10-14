package com.oz.springmvc.framework.handlermapping;

import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;

public class ControllerClassNameHandlerMapping extends AbstractControllerUrlHandlerMapping
{
  private static final String CONTROLLER_SUFFIX = "Controller";
  private boolean caseSensitive;
  private String pathPrefix;
  private String basePackage;

  public ControllerClassNameHandlerMapping()
  {
    this.caseSensitive = false;
  }

  public void setCaseSensitive(boolean caseSensitive)
  {
    this.caseSensitive = caseSensitive;
  }

  public void setPathPrefix(String prefixPath)
  {
    this.pathPrefix = prefixPath;
    if (StringUtils.hasLength(this.pathPrefix)) {
      if (!(this.pathPrefix.startsWith("/"))) {
        this.pathPrefix = "/" + this.pathPrefix;
      }
      if (this.pathPrefix.endsWith("/"))
        this.pathPrefix = this.pathPrefix.substring(0, this.pathPrefix.length() - 1);
    }
  }

  public void setBasePackage(String basePackage)
  {
    this.basePackage = basePackage;
    if ((StringUtils.hasLength(this.basePackage)) && (!(this.basePackage.endsWith("."))))
      this.basePackage += ".";
  }

  protected String[] buildUrlsForHandler(String beanName, Class beanClass)
  {
    return generatePathMappings(beanClass);
  }

  protected String[] generatePathMappings(Class beanClass)
  {
    StringBuffer pathMapping = buildPathPrefix(beanClass);
    String className = ClassUtils.getShortName(beanClass);
    String path = (className.endsWith("Controller")) ? className.substring(0, className.indexOf("Controller")) : className;

    if (path.length() > 0) {
      if (this.caseSensitive) {
        pathMapping.append(path.substring(0, 1).toLowerCase()).append(path.substring(1));
      }
      else {
        pathMapping.append(path.toLowerCase());
      }
    }
    if (isMultiActionControllerType(beanClass)) {
      return new String[] { pathMapping.toString(), pathMapping.toString() + "/*" };
    }

    return new String[] { pathMapping.toString() + "*" };
  }

  private StringBuffer buildPathPrefix(Class beanClass)
  {
    StringBuffer pathMapping = new StringBuffer();
    if (this.pathPrefix != null) {
      pathMapping.append(this.pathPrefix);
      pathMapping.append("/");
    }
    else {
      pathMapping.append("/");
    }
    if (this.basePackage != null) {
      String packageName = ClassUtils.getPackageName(beanClass);
      if (packageName.startsWith(this.basePackage)) {
        String subPackage = packageName.substring(this.basePackage.length()).replace('.', '/');
        pathMapping.append((this.caseSensitive) ? subPackage : subPackage.toLowerCase());
        pathMapping.append("/");
      }
    }
    return pathMapping;
  }
}