# Stage 1: Build the application
FROM bellsoft/liberica-openjdk-alpine:17 as builder
COPY . .
CMD ["./gradlew", "clean", "build"]

# Stage 2: Run the application
FROM bellsoft/liberica-openjre-alpine:17 AS runner
COPY --from=builder /build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]