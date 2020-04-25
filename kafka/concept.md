*Kafka node liveness has two conditions*

 * A node must be able to maintain its session with ZooKeeper (via ZooKeeper's heartbeat mechanism)
 * If it is a follower it must replicate the writes happening on the leader and not fall "too far" behind
