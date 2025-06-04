# Use Ubuntu 22.04 as the base image
FROM ubuntu:22.04

# Set environment variables to avoid interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

ARG CACHEBUST=1
RUN echo $CACHEBUST

# Update the package list and install required packages
RUN apt-get update && apt-get install -y \
    curl \
    gnupg2 \
    lsb-release \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Add NodeSource APT repository for Node.js 20.x and install Node.js
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs

# Verify the installation
RUN node -v && npm -v

# Set the working directory
WORKDIR /usr/src/app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY src/ ./src/
COPY webpack.config.js ./webpack.config.js
COPY webpack.config.dev.js ./webpack.config.dev.js
COPY .env ./.env

# Build the project
RUN npm run build

# Expose the application port
EXPOSE 9001

# Copy and set permissions for startup script
COPY *.sh /
RUN chmod +x /script.sh

# Start the app
CMD ["sh", "-c", "/script.sh"]
