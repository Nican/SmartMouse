################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/SmartMouse.c \
../src/maze.c \
../src/mazelogic.c \
../src/robot.c \
../src/robotlogic.c 

OBJS += \
./src/SmartMouse.o \
./src/maze.o \
./src/mazelogic.o \
./src/robot.o \
./src/robotlogic.o 

C_DEPS += \
./src/SmartMouse.d \
./src/maze.d \
./src/mazelogic.d \
./src/robot.d \
./src/robotlogic.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -Im -O0 -g3 -Wall -Wextra -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


