cmake_minimum_required(VERSION 3.12)

project(LW4)

set(SOURCES 
    LW4.cpp
)

add_executable(Solution ${SOURCES})

add_custom_command(TARGET Solution POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E copy
    ${CMAKE_SOURCE_DIR}/receiver.txt
    ${CMAKE_SOURCE_DIR}/sender.bin
    $<TARGET_FILE_DIR:Solution>
)