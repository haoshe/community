# Community Forum

A full-featured community forum platform built with Spring Boot, enabling users to create threads, engage in discussions, and build connections through a modern web interface.

## 🌟 Features

### Core Functionality
- **Thread Management**: Create, view, and interact with discussion threads
- **Sorting Options**: Sort threads by date or popularity
- **Content Moderation**: Automatic filtering of sensitive content to ensure appropriate discussions
- **Guest Access**: Non-registered users can browse and read posts (read-only access)

### User Interaction
- **Detailed Thread Views**: Click any thread to view full content and engage with the community
- **Engagement System**: Like threads and participate in discussions
- **Multi-level Comments**: Comment on threads and reply to other users' comments
- **Real-time Notifications**: Receive system notifications when others interact with your content

### Authentication & Security
- **User Registration**: Secure account creation with email verification
- **Email Activation**: Account activation via email confirmation link
- **Secure Login**: Login system with dynamic validation codes that refresh on each page load
- **Session Management**: Persistent login sessions - stay logged in across browser sessions
- **Spring Security**: Enterprise-grade security implementation

### User Profiles & Social Features
- **Personal Dashboard**: View your profile statistics and activity
- **Follow System**: Follow other users and track your followers
- **Activity Tracking**: Monitor your likes, posts, and engagement metrics
- **Public Profiles**: Other users can view your profile and follow you
- **Social Analytics**: Track follower counts and engagement statistics

### Advanced Features
- **Search Functionality**: Find threads using keyword search
- **Admin Dashboard**: Moderator access to UV (Unique Visitors) and active user analytics
- **Notification System**: Stay updated with system-wide announcements and personal notifications

## 🔧 Technology Stack

### Backend Framework
- **Spring Boot** - Main application framework
- **Spring MVC** - Web layer architecture
- **MyBatis** - Data persistence layer
- **Spring Security** - Authentication and authorization
- **Spring Actuator** - Application monitoring and management

### Database & Caching
- **MySQL** - Primary database
- **Redis** - Caching and session storage

### Message Queue & Search
- **Apache Kafka** - Event streaming and message processing
- **Elasticsearch** - Full-text search capabilities

### Development Tools
- **Apache Maven** - Dependency management and build automation
- **IntelliJ IDEA** - Integrated development environment
- **Apache Tomcat** - Application server
- **Git** - Version control system

## 📖 Development Log

Check out the detailed development progress and technical decisions in the [dev-log](dev-log/) directory.

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

Built with ❤️ using Spring Boot and modern web technologies.
