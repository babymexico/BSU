#include "pch.h"
#include <gtest/gtest.h>
#include "message_handler.h"
#include <thread>

TEST(SenderReceiverTest, SendMessageAndReceive) {
    std::string sender_file = "sender_test.txt";
    std::string receiver_file = "receiver_test.txt";
    std::string message_to_send = "Hello, receiver!";

    std::string received_message;
    std::thread receiver_thread(receiver, std::ref(receiver_file), std::ref(received_message));
    std::thread sender_thread(sender, std::ref(sender_file), std::cref(message_to_send));
    receiver_thread.join();
    sender_thread.join();

    std::ifstream receiver_input(receiver_file);
    std::string content;
    std::getline(receiver_input, content, '\0');

    EXPECT_EQ(message_to_send, content);
}

int main(int argc, char** argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
