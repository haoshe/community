# Using .env Files with Spring Boot in IntelliJ IDEA

This guide shows how to securely manage environment variables in Spring Boot applications using `.env` files and IntelliJ IDEA's EnvFile plugin.

## Why Use Environment Variables?

Environment variables help you:
- Keep sensitive data (passwords, API keys) out of your source code
- Use different configurations for development, testing, and production
- Follow security best practices by not committing secrets to version control

## Prerequisites

- IntelliJ IDEA (Community or Ultimate)
- Spring Boot project
- EnvFile plugin

## Step-by-Step Setup

### 1. Install the EnvFile Plugin

1. Open IntelliJ IDEA
2. Go to **File → Settings** (Windows/Linux) or **IntelliJ IDEA → Preferences** (macOS)
3. Navigate to **Plugins**
4. Search for "**EnvFile**" in the Marketplace
5. Install and restart IntelliJ

### 2. Create Your .env File

1. Create a `.env` file in your project root directory (same level as `pom.xml` or `build.gradle`)
2. Add your environment variables:

```env
# Email Configuration
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=your.email@gmail.com
SPRING_MAIL_PASSWORD=your-app-password

# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/your_database
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_db_password

# Other Configuration
API_KEY=your-secret-api-key
JWT_SECRET=your-jwt-secret
```

**Note for macOS users:** `.env` files are hidden by default. Press `Shift + Cmd + .` to toggle hidden file visibility in Finder.

### 3. Update application.properties

Replace hardcoded values with environment variable placeholders using the `${VARIABLE_NAME}` syntax:

```properties
# Mail Configuration
spring.mail.host=${SPRING_MAIL_HOST}
spring.mail.port=${SPRING_MAIL_PORT}
spring.mail.username=${SPRING_MAIL_USERNAME}
spring.mail.password=${SPRING_MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Database Configuration
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

# Other Configuration
app.api.key=${API_KEY}
app.jwt.secret=${JWT_SECRET}
```

### 4. Configure IntelliJ Run Configurations

#### For Main Application:
1. Go to **Run → Edit Configurations**
2. Select your Spring Boot application configuration
3. Click the **EnvFile** tab
4. Check "**Enable EnvFile**"
5. Click the **+** button and select your `.env` file
6. Click **OK**

#### For JUnit Tests:
1. Go to **Run → Edit Configurations**
2. Select your test configuration (or create a new JUnit template)
3. Follow the same steps as above to enable EnvFile
4. This ensures your tests can access the same environment variables

### 5. Secure Your .env File

**Critical:** Add `.env` to your `.gitignore` file to prevent committing sensitive data:

```gitignore
# Environment variables
.env
.env.local
.env.*.local
```

## Best Practices

### Security
- **Never commit `.env` files** containing real credentials to version control
- Use **App Passwords** instead of your actual email password for Gmail
- **Rotate credentials immediately** if they're accidentally exposed
- Consider using different `.env` files for different environments (`.env.dev`, `.env.test`, `.env.prod`)

### Team Collaboration
- Create a `.env.example` file with placeholder values:
  ```env
  SPRING_MAIL_HOST=smtp.gmail.com
  SPRING_MAIL_PORT=587
  SPRING_MAIL_USERNAME=your-email-here
  SPRING_MAIL_PASSWORD=your-app-password-here
  ```
- Commit the `.env.example` file so team members know what variables are needed
- Document the setup process in your README

## Troubleshooting

### Common Issues

**Variables not loading:**
- Ensure EnvFile is enabled in your run configuration
- Check that your `.env` file is in the project root
- Verify there are no spaces around the `=` sign in your `.env` file
- Restart IntelliJ after installing the EnvFile plugin

**Tests failing:**
- Make sure EnvFile is enabled for your test configurations
- Consider creating separate test configurations with test-specific environment variables

**File not visible on macOS:**
- Use `Shift + Cmd + .` to show hidden files
- Alternatively, create the file using IntelliJ: Right-click project root → New → File → `.env`

## Alternative Approaches

If you prefer not to use the EnvFile plugin, you can also:
- Set environment variables at the system level
- Use IntelliJ's built-in environment variables section in run configurations
- Use Spring profiles with different `application-{profile}.properties` files

## Summary

Using `.env` files with Spring Boot and IntelliJ provides a clean, 
secure way to manage configuration without exposing sensitive data in 
your codebase. Remember to always keep your `.env` files out of version
control and follow security best practices when handling credentials.