/**
 * This package contains the model classes for the Music Library Web application.
 * It includes entity classes for managing {@link Song} and {@link Album}
 * entities, as well as a custom exception class.
 *
 * The {@link Song} class represents individual songs with attributes such as
 * title, author, album association, release date, and duration. The
 * {@link Album} class encapsulates details about music albums, each of which
 * can contain multiple {@link Song} instances, establishing a one-to-many
 * relationship.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
package mk.musiclibraryweb.models;
